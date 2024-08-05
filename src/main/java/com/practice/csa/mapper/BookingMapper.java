package com.practice.csa.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.csa.entity.Booking;
import com.practice.csa.entity.Service;
import com.practice.csa.responseDto.BookingResponse;
import com.practice.csa.responseDto.ServiceResponse;

@Component
public class BookingMapper {

	@Autowired
	private CarMapper carMapper;

	@Autowired
	private ServiceMapper serviceMapper;

	public BookingResponse mapToBookingResponse(Booking booking) {
		BookingResponse bookingResponse = new BookingResponse();
		bookingResponse.setId(booking.getId());
		bookingResponse.setCar(carMapper.mapToCarResponse(booking.getCar()));
		
		List<ServiceResponse> response = booking.getContracts().stream().map(contract -> {
			Service service = contract.getService();
			return serviceMapper.mapToServiceResponse(service);
		}).toList();
		
		bookingResponse.setServices(response);
		
		return bookingResponse;
	}

}
