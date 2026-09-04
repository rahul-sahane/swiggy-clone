package com.swiggy.userservice.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//return user profile info (e.g., "GET /users/{id}")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	
	private Long id;
	
	private String fullName;
	
	private String email;
	
	private String phoneNumber;
	
	private String role;
	
	@JsonProperty("isActive")
	private boolean isActive;
	
	private LocalDateTime createdAt;
}
