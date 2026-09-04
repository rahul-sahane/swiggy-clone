package com.swiggy.userservice.service;

import com.swiggy.userservice.dto.request.LoginRequest;
import com.swiggy.userservice.dto.request.SignupRequest;
import com.swiggy.userservice.dto.response.AuthResponse;
import com.swiggy.userservice.dto.response.UserResponse;

public interface UserService {
	
	AuthResponse signup(SignupRequest request);
	
	AuthResponse login(LoginRequest request);
	
	UserResponse getCurrentUser(String email);
}
