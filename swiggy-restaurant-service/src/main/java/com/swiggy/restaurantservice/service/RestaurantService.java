package com.swiggy.restaurantservice.service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.swiggy.restaurantservice.dto.request.RestaurantRequest;
import com.swiggy.restaurantservice.dto.request.UpdateRestaurantRequest;
import com.swiggy.restaurantservice.dto.response.RestaurantDetailsResponse;
import com.swiggy.restaurantservice.dto.response.RestaurantResponse;
import com.swiggy.restaurantservice.identity.AuthenticatedUser;

import lombok.RequiredArgsConstructor;

//The interface describes WHAT service can do
@Service
@RequiredArgsConstructor
public interface RestaurantService {
	
	//Create a brand new restaurant, owned by who's making the request
	RestaurantResponse createRestaurant(AuthenticatedUser actor, RestaurantRequest request);
	
	//Fetch restaurant by its Database id
	RestaurantResponse getRestuarantById(Long id);
	
	//Pageable means "gives me page 2, 20 result per page, sorted by rating"
	//instead of dumping every single row in the table into response.
	Page<RestaurantResponse> searchRestaurants(String city, String cuisinetype, Boolean open, Pageable pageable);
	
	//"Show me My Restaurant" - the owner dashboard. this does needs an
	//actor, bacause "mine" only means something once we know who's asking.
	Page<RestaurantResponse> getMyRestaurants(AuthenticatedUser actor, Pageable pageable);
	
	//PUT-style full update: the caller must resend every editadble field
	//not just the that ones changed. 'actor' prove they are allowed to touch to restuarant id
	RestaurantResponse updateRestuarant(AuthenticatedUser actor, Long id, UpdateRestaurantRequest request);
	
	//Kept separate from the full update above so a
    // restaurant owner can open/close for the day without resending their
    // whole address and cuisine type every time.
	RestaurantResponse updateOpenStatus(AuthenticatedUser actor, Long id, boolean isOpen);
	
	//Delete a restaurant
	void deleteRestauarant(AuthenticatedUser actor, Long id);
	
	//Below All this line is a read-only "search/filter" method.
	List<RestaurantResponse> getRestuarantsByCity(String city);
	
	List<RestaurantResponse> getReaurantsByCuisine(String cuisineType);
	
	List<RestaurantResponse> getRestaurantsByCityAndCuisine(String city, String cuisionType);
	
	List<RestaurantResponse> getRestaurantsByOwner(Long ownerId);
	
	List<RestaurantResponse> getRestaurantsWithMinimumRating(BigDecimal minRating);
	
	List<RestaurantResponse> getRestaurantsByOpenStatus(boolean isOpen);
	
	List<RestaurantResponse> getRestuarantsByCityOpenStatusAndMinimumRating(String city, boolean isOpen, BigDecimal minRating);
	
	List<RestaurantResponse> getTopRatedRestaurantsByCity(String city);
	
	List<RestaurantResponse> searchRestaurantsByName(String name);
	
	List<RestaurantResponse> getRestaurantsByCities(List<String> cities);
	
	Long countRestaurantsByOwner(Long ownerId);
	
	BigDecimal getAverageRestaurantRating();
	
	BigDecimal getAverageRestaurantRatingByCity(String city);
	
	Long countRestaurantByCityAndCuisine(String city, String cuisinType);
	
	//Return a restaurant AND its full menu together in one response
	//so we doesn't need to make restuarant's details page separately
	RestaurantDetailsResponse getRestuarantWithMenuItems(Long id);
	
	List<RestaurantResponse> getTopRatedOpenRestaurantsByCity(String city, int limit);
	
	List<RestaurantResponse> getRestaurantsWithMenu();
	
	Map<Boolean, Long> countRestuarantByStatus();
	
	List<RestaurantResponse> getRestaurantsByOwnerAndCity(Long ownerId, String city);
	
	boolean doesOwnerHaveRestaurantInCity(Long ownerId, String city);
	
	
 }
