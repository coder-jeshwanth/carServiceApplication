package com.practice.csa.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.User;
import com.practice.csa.exception.ServiceNotFoundByIdException;
import com.practice.csa.exception.UserNotFoundByIdException;
import com.practice.csa.mapper.UserMapper;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.requestDto.UserRequest;
import com.practice.csa.responseDto.ServiceResponse;
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
		
		return userRepository.findById(userId)
		        .map(user -> {
		            userRepository.delete(user);
		                return ResponseEntity
		                .status(HttpStatus.OK)
		                .body(new ResponseStructure<UserResponse>()
		                    .setStatuscode(HttpStatus.OK.value())
		                    .setMessage("Service Object deleted successfully!!")
		                    .setData(userMapper.mapToUserResponse(user)));
		        })
		        .orElseThrow(() -> new UserNotFoundByIdException("Service object not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser() {
		
		List<UserResponse> responses = userRepository.findAll()
				.stream()
				.map(user -> userMapper.mapToUserResponse(user))
				.toList();
		
		if (responses.isEmpty()) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body(new ResponseStructure<List<UserResponse>>()
	                        .setStatuscode(HttpStatus.NOT_FOUND.value())
	                        .setMessage("No User Objects found")
	                        .setData(responses));
	    } else {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(new ResponseStructure<List<UserResponse>>()
	                        .setStatuscode(HttpStatus.OK.value())
	                        .setMessage("User Objects found successfully!!")
	                        .setData(responses));
	    }
		}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(int userId) {
		
		return userRepository.findById(userId)
				.map(user -> ResponseEntity
						 .status(HttpStatus.FOUND)
						 .body(new ResponseStructure<UserResponse>()
								 .setStatuscode(HttpStatus.FOUND.value())
								 .setMessage("User Object found successfully!!")
								 .setData(userMapper.mapToUserResponse(user))))
			.orElseThrow(() -> new UserNotFoundByIdException("user object not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updatedByUserId(int id, UserRequest userRequest) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return userRepository.findByEmail(email)
		        .map(existingUser -> {
		            // Update the existing user with the values from UserRequest
		            existingUser.setUserName(userRequest.getName());
		            existingUser.setEmail(userRequest.getEmail());
		            existingUser.setPassword(userRequest.getPassword());		            

		            // Save the updated user
		            userRepository.save(existingUser);

		            return ResponseEntity
		                .status(HttpStatus.OK)
		                .body(new ResponseStructure<UserResponse>()
		                    .setStatuscode(HttpStatus.OK.value())
		                    .setMessage("user Object updated successfully!!")
		                    .setData(userMapper.mapToUserResponse(existingUser)));
		        })
		        .orElseThrow(() -> new UserNotFoundByIdException("User object not found"));
	}


	
}
