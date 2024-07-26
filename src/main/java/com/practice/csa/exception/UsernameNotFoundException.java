package com.practice.csa.exception;

public class UsernameNotFoundException extends RuntimeException {
	
	private String message;

	public UsernameNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
