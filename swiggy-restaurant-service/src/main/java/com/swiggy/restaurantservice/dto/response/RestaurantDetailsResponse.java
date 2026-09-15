package com.swiggy.restaurantservice.dto.response;

import java.util.List;

import com.swiggy.restaurantservice.entity.MenuItem;
import com.swiggy.restaurantservice.entity.Restaurant;

public record RestaurantDetailsResponse(
		Restaurant restaurant,
		List<MenuItem> menuItems) {
}
