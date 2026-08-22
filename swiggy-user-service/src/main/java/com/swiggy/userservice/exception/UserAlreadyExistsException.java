package com.swiggy.userservice.exception;

// Costum exception - thrown when someone tries to sign up with an email
public class UserAlreadyExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
