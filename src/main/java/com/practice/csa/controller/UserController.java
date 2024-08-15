package com.practice.csa.controller;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.requestDto.LoginRequest;
import com.practice.csa.requestDto.ServiceRequest;
import com.practice.csa.requestDto.UserRequest;
import com.practice.csa.responseDto.ServiceResponse;
import com.practice.csa.responseDto.UserResponse;
import com.practice.csa.security.JwtService;
import com.practice.csa.service.UserService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody UserRequest userRequest) {
		return userService.addUser(userRequest);	
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(@PathVariable int userId) {
		return userService.deleteByUserId(userId);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser() {
		return userService.findAllUser();
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(@PathVariable int userId) {
		return userService.findByUserId(userId);
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateByUserId(@PathVariable int userId,@RequestBody UserRequest userRequest) {
		return userService.updatedByUserId(userId,userRequest);
	}	
	
	@PostMapping("/user-login")
	public String login(){
		return jwtService.createJwt("kittu@gmail.com","CUSTOMER", Duration.ofDays(1));
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<String>> login(@RequestBody LoginRequest loginRequest){
		return userService.login(loginRequest);
	}
	
	@GetMapping("/get")
	public String get() {
		return "hello";
	}
}
