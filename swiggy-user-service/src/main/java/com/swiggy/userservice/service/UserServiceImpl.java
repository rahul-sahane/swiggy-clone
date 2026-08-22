package com.swiggy.userservice.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swiggy.userservice.dto.request.LoginRequest;
import com.swiggy.userservice.dto.request.SignupRequest;
import com.swiggy.userservice.dto.response.AuthResponse;
import com.swiggy.userservice.entity.User;
import com.swiggy.userservice.exception.UserAlreadyExistsException;
import com.swiggy.userservice.repository.UserRepository;
import com.swiggy.userservice.security.Jwtutil;

import lombok.RequiredArgsConstructor;

@Service					// creating One Instance
@RequiredArgsConstructor	//Auto generated constructor for all "final" fields

public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private AuthenticationManager authenticationManager;
	
	private Jwtutil jwtUtil;
	
	@Override
	public AuthResponse signup(SignupRequest request) {
		
		//1. Check if this email is already registered
		if(userRepository.existByEmail(request.getEmail())) {
			throw new UserAlreadyExistsException("Email already registered : " + request.getEmail());
		}
		
		//2. Building a new User entity from the incoming request
		User user = new User();
		user.setFullName(request.getFullName());
		user.setEmail(request.getEmail());
		
		//hashing the password before saving - password always hash first via passwordEncode , BCrypt
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		user.setPhoneNumber(request.getPhoneNumber());
		user.setRole(request.getRole());
		user.setActive(true);
		// createdAt/updatedAt are auto-filled By @PrePersist in the user entity
		
		//3. now saving to Database
		User savedUser = userRepository.save(user);
		
		//4. Generate a JWT token for this new user, so they're auto-logged-in after signup
		String token = jwtUtil.generateToken(savedUser.getEmail());
		
		//5. Building and returning the response (but without password)
		return new AuthResponse (
				token,
				savedUser.getId(),
				savedUser.getFullName(),
				savedUser.getEmail(),
				savedUser.getRole()
				);
	}

	@Override
	public AuthResponse login(LoginRequest request) {
		
		//1. Here Spring Security verify the email + password match what's in the database
		//If they don't match this line throws BadCredentionalsException automatically.
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
				);
		
		//2. If credential is correct then fetch then full user record
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
		
		//3.Generating a fresh JWT token for this login session
		String token = jwtUtil.generateToken(user.getEmail());
		
		//return the response
		return new AuthResponse(
				token,
				user.getId(),
				user.getFullName(),
				user.getEmail(),
				user.getRole()
				);
	}

}
