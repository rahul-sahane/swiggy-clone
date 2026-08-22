package com.swiggy.userservice.service;

import com.swiggy.userservice.dto.request.LoginRequest;
import com.swiggy.userservice.dto.request.SignupRequest;
import com.swiggy.userservice.dto.response.AuthResponse;

public interface UserService {
	
	AuthResponse signup(SignupRequest request);
	
	AuthResponse login(LoginRequest request);
}
