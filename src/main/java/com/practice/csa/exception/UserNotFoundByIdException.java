package com.practice.csa.exception;

public class UserNotFoundByIdException extends RuntimeException{

	private String message;

	public UserNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	
}
