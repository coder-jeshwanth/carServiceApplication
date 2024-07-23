package com.practice.csa.mapper;

import org.springframework.stereotype.Component;

import com.practice.csa.entity.User;
import com.practice.csa.requestDto.UserRequest;
import com.practice.csa.responseDto.UserResponse;

@Component
public class UserMapper {
	public User mapToUser(UserRequest request) {
		User user = new User();
		
		user.setUserName(request.getName());
		user.setUserEmailId(request.getEmail());
		user.setUserPassword(request.getPassword());
		user.setUserRole(request.getUserRole());
		
		return user;
	}
	
	public UserResponse mapToUserResponse(User user) {
		
		UserResponse userResponse = new UserResponse();
		
		userResponse.setId(user.getUserId());
		userResponse.setName(user.getUserName());
		userResponse.setEmail(user.getUserEmailId());
		userResponse.setUserRole(user.getUserRole());
		
		return userResponse;
	}
}
