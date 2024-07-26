package com.practice.csa.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.practice.csa.entity.User;
import com.practice.csa.requestDto.UserRequest;
import com.practice.csa.responseDto.UserResponse;

@Component
public class UserMapper {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User mapToUser(UserRequest request) {
		User user = new User();
		
		user.setUserName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setUserRole(request.getUserRole());
		
		return user;
	}
	
	public UserResponse mapToUserResponse(User user) {
		
		UserResponse userResponse = new UserResponse();
		
		userResponse.setId(user.getUserId());
		userResponse.setName(user.getUserName());
		userResponse.setEmail(user.getEmail());
		userResponse.setUserRole(user.getUserRole());
		
		return userResponse;
	}
}
