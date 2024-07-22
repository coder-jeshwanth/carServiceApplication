package com.practice.csa.exception;

public class ServiceNotFoundByIdException extends RuntimeException {

	private String message;

	public ServiceNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
