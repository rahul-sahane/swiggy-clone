package com.swiggy.restaurantservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swiggy.restaurantservice.entity.MenuItem;
import com.swiggy.restaurantservice.entity.Restaurant;

@Repository
public interface ManuItemRepository extends JpaRepository<Restaurant, Long>{

	// Translates to: SELECT * FROM menu_items WHERE restaurant_id = ?
    // Since MenuItem has a @ManyToOne "restaurant" field, we write
    // "Restaurant_Id" (matching the field name + its id) - Spring Data JPA
	List<MenuItem> findByReataurant_id(Long Restaurant_id);
}
