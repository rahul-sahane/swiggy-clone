package com.swiggy.userservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiggy.userservice.dto.request.LoginRequest;
import com.swiggy.userservice.dto.request.SignupRequest;
import com.swiggy.userservice.dto.response.AuthResponse;
import com.swiggy.userservice.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	
	 private final UserService userService ;
	 
	    // @Valid triggers SignupRequest's validation annotations before this method runs
	    @PostMapping("/signup")
	    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignupRequest request) {
	    	
	        AuthResponse response = userService.signup(request);
	        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 - new user created
	    }
	 
	    @PostMapping("/login")
	    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
	    	
	        AuthResponse response = userService.login(request);
	        return ResponseEntity.ok(response); // 200 - existing user, just returning a token
	    }
}
