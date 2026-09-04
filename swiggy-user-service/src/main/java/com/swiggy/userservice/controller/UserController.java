package com.swiggy.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.userservice.dto.response.UserResponse;
import com.swiggy.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/me")
	public ResponseEntity<UserResponse> getMyProfile(Authentication authentication){
		
        // Authentication.getName() returns the email - that's what JwtAuthFilter
        // set as the "username" when it built the authenticated user's identity
		String email = authentication.getName();
		
		UserResponse response = userService.getCurrentUser(email);
		return ResponseEntity.ok(response);
		
	}
}
