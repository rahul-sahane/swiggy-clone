package com.swiggy.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

//This class represent the JSON data a client when signing up

@Data
public class SignupRequest {
	
	@NotBlank(message = "Full name is required")
	@Size(min = 2, max = 150, message = "Full name must between 2 to 150")
	private String fullName;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 character")
	private String password;
	
	@NotBlank(message = "Phone numbe is required")
	@Pattern(regexp = "^[0-9]{10}$" , message = "Phone number must be exactly 10 digit")
	private String phoneNumber;
	
	@NotBlank(message = "Role is required")
	@Pattern(
			regexp = "CUSTOMER|RESTAURANT_OWNER|DELIVERY_PARTNER|ADMIN",
			message = "Role must be one of: CUSTOMER, RESTAURANT_OWNER, DELIVERY_PARTNER, ADMIN"
			)
	private String role;
}
