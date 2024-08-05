package com.practice.csa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.requestDto.ServiceRequest;
import com.practice.csa.responseDto.CartResponse;
import com.practice.csa.responseDto.ServiceResponse;
import com.practice.csa.service.CartService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/carts/{serviceId}")
	public ResponseEntity<ResponseStructure<CartResponse>> addServiceToCart(@PathVariable int serviceId) {
		return cartService.addServiceToCart(serviceId);	
	}
	
}
