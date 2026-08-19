package com.swiggy.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Loginrequest {
	
	@NotBlank(message = "email is required")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Password is required")
	private String password;
}
