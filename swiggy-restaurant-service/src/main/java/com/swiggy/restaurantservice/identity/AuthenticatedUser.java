package com.swiggy.restaurantservice.identity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticatedUser(Long id, String fullName, String email, String role,
								@JsonProperty("isActive") boolean active) {
	public boolean canManagerRestaurants() {
		return active && ("RESTAURANT_OWNER".equals(role) || "ADMIN".equals(role));
	}
}
