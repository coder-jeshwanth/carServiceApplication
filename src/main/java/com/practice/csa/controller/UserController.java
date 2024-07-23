package com.practice.csa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.requestDto.UserRequest;
import com.practice.csa.responseDto.UserResponse;
import com.practice.csa.service.UserService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody UserRequest userRequest) {
		return userService.addUser(userRequest);	
	}
}
