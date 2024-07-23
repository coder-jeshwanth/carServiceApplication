package com.practice.csa.service;

import org.springframework.http.ResponseEntity;

import com.practice.csa.requestDto.UserRequest;
import com.practice.csa.responseDto.UserResponse;
import com.practice.csa.utility.ResponseStructure;

public interface UserService {

	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest);
}
