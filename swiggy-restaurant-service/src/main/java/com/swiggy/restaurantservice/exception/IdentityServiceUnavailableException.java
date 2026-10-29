package com.swiggy.restaurantservice.exception;

public class IdentityServiceUnavailableException extends RuntimeException{
	IdentityServiceUnavailableException(String message){
		super(message);
	}
}
