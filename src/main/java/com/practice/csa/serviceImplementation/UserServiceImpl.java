package com.practice.csa.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.User;
import com.practice.csa.mapper.UserMapper;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.requestDto.UserRequest;
import com.practice.csa.responseDto.UserResponse;
import com.practice.csa.service.UserService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest) {
        User user = userMapper.mapToUser(userRequest);
		
		user = userRepository.save(user);
		
		UserResponse userResponse = userMapper.mapToUserResponse(user);
		
		ResponseStructure<UserResponse> responseStructure = new ResponseStructure<UserResponse>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("User Object created successfully!!");
		responseStructure.setData(userResponse);
		 
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updatedByUserId(int id, UserRequest userRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
