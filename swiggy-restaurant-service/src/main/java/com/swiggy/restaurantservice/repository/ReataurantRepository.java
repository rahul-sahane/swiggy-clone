package com.swiggy.restaurantservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiggy.restaurantservice.entity.Restaurant;

public interface ReataurantRepository extends JpaRepository<Restaurant, Long> {
	
	//SELECT * FROM restaurants WHERE city = ?
	List<Restaurant> findByCity(String city);
	
	//SELECT * FROM restaurants WHERE owner_id = ?
	List<Restaurant> findByOwnerId(String owner_id);
}
