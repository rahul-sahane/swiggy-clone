package com.swiggy.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This class what we send Back to the client after a successful signup or login.
// note : No password field here. we never send the password back, even hashed

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	
	private String token;      //JWT token - client store this and sends it on future requests 
	
	private Long userID;
	
	private String fullName;
	
	private String email;
	
	private String role;
}
