package com.swiggy.restaurantservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//what we send back to the client when they request

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class RestaurantResponse {
 
	private String id;
	private String ownerId;
	private String name;
	private String address;
	private String city;
	private String cuisineType;
	private BigDecimal rating;
	
	// we want to send isOpen in the response, but we don't want to use the same name as the field, so we will used @JsonProperty to change
	@JsonProperty("isOpen")
	private boolean open;
	
	
	private LocalDateTime createdAt; 
	private LocalDateTime updatedAt; 
}
