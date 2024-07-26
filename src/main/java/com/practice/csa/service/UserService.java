package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.requestDto.UserRequest;
import com.practice.csa.responseDto.UserResponse;
import com.practice.csa.utility.ResponseStructure;

public interface UserService {

	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest);
	
	public ResponseEntity<ResponseStructure<UserResponse>>  deleteByUserId(int userId);
	
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser();
	
	public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(int userId);
	
	public ResponseEntity<ResponseStructure<UserResponse>> updatedByUserId(int id, UserRequest userRequest);
}
