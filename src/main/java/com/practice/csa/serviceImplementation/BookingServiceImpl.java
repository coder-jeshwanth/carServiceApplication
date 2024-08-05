package com.practice.csa.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.Booking;
import com.practice.csa.entity.Car;
import com.practice.csa.entity.Cart;
import com.practice.csa.entity.Contract;
import com.practice.csa.entity.User;
import com.practice.csa.exception.CarNotFoundByIdException;
import com.practice.csa.exception.UserNotFoundByIdException;
import com.practice.csa.mapper.BookingMapper;
import com.practice.csa.repository.BookingRepository;
import com.practice.csa.repository.CarRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.responseDto.BookingResponse;
import com.practice.csa.service.BookingService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private BookingMapper bookingMapper;
	
	@Override
	public ResponseEntity<ResponseStructure<BookingResponse>> createBooking(int carId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found"));
		Booking booking = new Booking();
		Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundByIdException("Car ID not found"));

		Cart cart = user.getCart();
		List<Contract> contracts = cart.getServices().stream().map(service -> {
			Contract contract = new Contract();
			contract.setService(service);
			contract.setBooking(booking);
			return contract;
		}).toList();

		
		booking.setCar(car);
		
		booking.setContracts(contracts);

		Booking savedBooking = bookingRepository.save(booking);
		BookingResponse bookingResponse = bookingMapper.mapToBookingResponse(savedBooking);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<BookingResponse>()
				.setStatuscode(HttpStatus.CREATED.value())
				.setMessage("Bookings created successfully").setData(bookingResponse));

	}

	
}
