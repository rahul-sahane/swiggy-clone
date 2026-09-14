package com.swiggy.restaurantservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

//What the CLIENT is allowed to send when creating a restaurant.

@Data
public class RestaurantRequest {
	
	@NotBlank(message = "Restaurant name is required")
	@Size(min =2, max = 150, message = "Restaurant name must be between 2 and 150 characters")
	private String name;
	
	@NotBlank(message = "Address is requird")
	@Size(max = 100, message = "Address must be at most 255 characters")
	private String city;
	
	@NotBlank(message = "Cuision type is required")
	@Size(max = 60, message = "Cuisine type must be at most 60 characters")
	private String cuisineType;
	
}
