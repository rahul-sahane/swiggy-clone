package com.swiggy.restaurantservice.identity;

public class ForbiddenExeption extends RuntimeException{
	public ForbiddenExeption(String message) {
		super(message);
	}
}
