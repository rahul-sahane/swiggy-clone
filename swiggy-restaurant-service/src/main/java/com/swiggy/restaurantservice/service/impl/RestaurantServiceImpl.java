package com.swiggy.restaurantservice.service.impl;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swiggy.restaurantservice.dto.request.RestaurantRequest;
import com.swiggy.restaurantservice.dto.request.UpdateRestaurantRequest;
import com.swiggy.restaurantservice.dto.response.RestaurantDetailsResponse;
import com.swiggy.restaurantservice.dto.response.RestaurantResponse;
import com.swiggy.restaurantservice.identity.AuthenticatedUser;
import com.swiggy.restaurantservice.repository.ManuItemRepository;
import com.swiggy.restaurantservice.repository.RestaurantRepository;
import com.swiggy.restaurantservice.service.RestaurantService;

import lombok.RequiredArgsConstructor;

// The two things this class needs to do its job - read/write restaurant
//and check whether a restaurant currently has menu items
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{
	
	private final RestaurantRepository restaurantRepository;
	private final ManuItemRepository menuItemRepository;
	
	@Override
	//@Transactional wrap this whole method in One database transaction
	//if anything inside the method throw exception, evry database
	//changes made so far in this method is automatically rolled back.
	@Transactional
	public RestaurantResponse createRestaurant(AuthenticatedUser actor, RestaurantRequest request) {
		
		//step 1 : check for duplicate Before trying to save
		if(restaurantRepository.existByOwnerIdAndNameIgnoreCase(actor.id(), request.getName())) {
			throw new DuplicateRestaurantException(
					"You already have a restaurant named : " + request.getName());
		}
		return null;
	}

	@Override
	public RestaurantResponse getRestuarantById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RestaurantResponse> searchRestaurants(String city, String cuisinetype, Boolean open,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RestaurantResponse> getMyRestaurants(AuthenticatedUser actor, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantResponse updateRestuarant(AuthenticatedUser actor, Long id, UpdateRestaurantRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantResponse updateOpenStatus(AuthenticatedUser actor, Long id, boolean isOpen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRestauarant(AuthenticatedUser actor, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RestaurantResponse> getRestuarantsByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> getReaurantsByCuisine(String cuisineType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> getRestaurantsByCityAndCuisine(String city, String cuisionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> getRestaurantsByOwner(Long ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> getRestaurantsWithMinimumRating(BigDecimal minRating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> getRestaurantsByOpenStatus(boolean isOpen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> getRestuarantsByCityOpenStatusAndMinimumRating(String city, boolean isOpen,
			BigDecimal minRating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> getTopRatedRestaurantsByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> searchRestaurantsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> getRestaurantsByCities(List<String> cities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countRestaurantsByOwner(Long ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getAverageRestaurantRating() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getAverageRestaurantRatingByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countRestaurantByCityAndCuisine(String city, String cuisinType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantDetailsResponse getRestuarantWithMenuItems(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> getTopRatedOpenRestaurantsByCity(String city, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> getRestaurantsWithMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Boolean, Long> countRestuarantByStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantResponse> getRestaurantsByOwnerAndCity(Long ownerId, String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doesOwnerHaveRestaurantInCity(Long ownerId, String city) {
		// TODO Auto-generated method stub
		return false;
	}

}
