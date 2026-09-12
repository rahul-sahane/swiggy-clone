package com.swiggy.restaurantservice.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swiggy.restaurantservice.entity.MenuItem;
import com.swiggy.restaurantservice.entity.Restaurant;

@Repository
public interface ManuItemRepository extends JpaRepository<Restaurant, Long>{

	// Translates to: SELECT * FROM menu_items WHERE restaurant_id = ?
    // Since MenuItem has a @ManyToOne "restaurant" field, we write
    // "Restaurant_Id" (matching the field name + its id) - Spring Data JPA
	List<MenuItem> findByReataurantId(Long RestaurantId);
	
	//Query - Spring generate: WHERE restaurant_id = ? And category = ?
	//ex. GET /api/restaurants/{id}/menu
	List<MenuItem> findByRestaurantIdAndCategory(Long restaurantId, String category);
	
	//Query - WHERE Restaurant_id = ? and is_available = ?
	//ex. GET /api/restaurants/{id}/menu?vegetarian=true
	List<MenuItem> findByRestaurantIdAndIsAvailable(Long restaurantId, boolean isVegetarian);
	
	//Query - WHERE Restaurant_id = ? and is_Available = ?
	//ex. only show available items to customers
	List<MenuItem> findByResturantIdAndIsAvailable(Long restaurant_id, boolean isAvailable);
	
	
	//Query - Multiple conditions
	//ex. GET /api/restaurants/{id}/menu?category=Main&vegetarian=true&available=true
	List<MenuItem> findByRestaurantIdAndCategoryAndIsVegetarianAndIsAvailable(
			Long RestaurantId, String catagory, boolean isVegetarian, boolean isAvailable);
	
	//Query with JPQl
	//used by : "show me cheap items" filter
	@Query("SELECT m FROM MenuItem m WHERE m.restaurant.id = :restaurantId AND m.price <=:maxPrice ORDER BY m.price ASC")
	List<MenuItem> findByRestaurantIdPriceLessThanEqual(
			@Param("restaurantId") Long restaurantId,
			@Param("maxPrice") BigDecimal maxPrice);
	
	//Query with Sorting
	// ex. returns : Average price of all items in a restaurant
	@Query("SELECT COUNT(m) FROM MenuItem m WHERE m.restaurant.id = restaurantId")
	BigDecimal findAveragePriceByRestaurant(@Param("restaurantId") Long restaurantId);
	
	//@Query for Counting
	//ex. Dashboard showing "123 item in menu"
	@Query("SELECT COUNT(m) FROM MenuItem m WHERE m.restaurant.id = :restaurantId")
	Long countbyRestaurantId(@Param("restaurantId") Long restaurantId);
	
	//@Query - check if item exists
	//ex. Validate before creating duplicates
	boolean existByRestaurantIdAndName(Long restaurantId, String name);
	
	boolean existByRestaurantId(Long retaurantId);
	
	// Query - N+ query
	@Query("SELECT m FROM MenuItem m JOIN FETCH m.Restaurant WHERE m.restaurant.id = :restaurantId")
	List<MenuItem> findByRestaurantIdWithRestaurant(@Param("restaurantId") Long restaurant);
	
	//Query - delete by restaurant
	void deleteByRestaurantId(Long restaurantId);
}
