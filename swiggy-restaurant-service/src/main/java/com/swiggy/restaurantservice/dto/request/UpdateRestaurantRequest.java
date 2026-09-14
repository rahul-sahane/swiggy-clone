package com.swiggy.restaurantservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

//Used by PUT /api/restaurants/{id}.
//PUT means "replace the whole thing, so every editable field is required"

@Data	
public class UpdateRestaurantRequest {
	
	@NotBlank(message = "Restaurant name is required")
	@Size(min = 2, max = 150, message = "Restaurant name must be between 2 and 150 characters")
	private String name;
	
	@NotBlank(message = "Address is required")
	@Size(max = 255, message = "Address must be at most 255 characters")
	private String address;
	
	@NotBlank(message = "City is required")
	@Size(max = 100, message = "City must be at most 100 characters")
	private String city;
	
	@NotBlank(message = "Cuision type is required")
	@Size(max = 60, message = "Cuisine type must be at most 60 characters")
	private String cuisineType;
	
	
}
