package com.practice.csa.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.Cart;
import com.practice.csa.exception.ServiceNotFoundByIdException;
import com.practice.csa.exception.UserNotFoundByIdException;
import com.practice.csa.mapper.ServiceMapper;
import com.practice.csa.repository.CartRepository;
import com.practice.csa.repository.ServiceRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.responseDto.CartResponse;
import com.practice.csa.responseDto.ServiceResponse;
import com.practice.csa.service.CartService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ServiceMapper serviceMapper;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	public ResponseEntity<ResponseStructure<CartResponse>> addServiceToCart(int serviceId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(user -> {
			Cart cart = user.getCart();
			if (cart.getServices() == null)
				cart.setServices(new ArrayList<com.practice.csa.entity.Service>());

			return serviceRepository.findById(serviceId).map(service -> {
				cart.getServices().add(service);
				Cart cart2 = cartRepository.save(cart);
				CartResponse cartResponse = new CartResponse();
				cartResponse.setCartId(cart2.getCartId());
				List<ServiceResponse> services = cart2.getServices().stream().map(serviceMapper::mapToServiceResponse)
						.toList();
				cartResponse.setServiceResponse(services);

				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseStructure<CartResponse>()
								.setStatuscode(HttpStatus.CREATED.value())
								.setMessage("Services Added to cart").setData(cartResponse));
			}).orElseThrow(() -> new ServiceNotFoundByIdException("service not found"));

		}).orElseThrow(()-> new UserNotFoundByIdException("user not found"));

	}

}
