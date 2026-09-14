package com.swiggy.restaurantservice.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRequest {
	
	@NotBlank(message = "Item name is required")
	@Size(min = 2, max = 120, message ="Item name must be between 2 and 120 character")
	private String description;
	
	@NotNull(message = "Price is required")
	@DecimalMin(value = "0.01", message = "Price must be greater than 0")
	@DecimalMax(value = "9999999.99", message = "Price is too high")
	@Digits(integer = 8, fraction = 2, message = "Price must have at most 8 digit before decimal and 2 after")
	private BigDecimal price;
	
	private boolean isVegetarian = false;
	
	private boolean isAvailable = true;
	
	@Size(max = 500, message = "Image URL cannot exceed 500 characters")
	@Pattern(regexp = "^(https?://.*\\.(jpg|jpeg|png|webp|gif))?$",
			flags = Pattern.Flag.CASE_INSENSITIVE,
			message = "Image URL must be a valid HTTp(S) URl ending in jpg, jpeg, png, webp, or gif")
	private String imageURL;
}
