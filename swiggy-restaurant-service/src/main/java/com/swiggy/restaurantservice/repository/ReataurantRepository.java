package com.swiggy.restaurantservice.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swiggy.restaurantservice.entity.Restaurant;

@Repository
//Repository interface for Restaurant entity.
public interface ReataurantRepository extends JpaRepository<Restaurant, Long> {
	
	//SELECT * FROM restaurants WHERE city = ?
	List<Restaurant> findByCityIgnoreCase(String city);
	
	List<Restaurant> findByCuisionTypeIgnoreCase(String cuisionType);
	
	List<Restaurant> findByCityIgnoreCaseAndCuisionTypeIgnoreCase(String city, String cuisionType);
	
	//Power's the owner's dashboardId 
	List<Restaurant> findByOwnerId(Long ownerID);
	
	//SELECT * FROM restaurants WHERE owner_id = ?
	List<Restaurant> findByOwnerId(String owner_id);
	
	// Backs up the uq_restaurants_owner_name constraint with a friendly
    // 409 response instead of a raw Oracle constraint-violation stack trace
	boolean existByOwnerIdAndNameIgnoreCase(Long ownerId, String name);
	
	//Show me restaurant by rating
	//ex. show me restaurants rated 4.0 or above
	List<Restaurant> findByRatingGreaterThanEqual(BigDecimal minRating);
	
	//filter by open status
	//ex. show me only open restaurants
	List<Restaurant> findByIsOpen(boolean isOpen);
	
	//Combine filter
	//ex . show me open restaurant in pune with rating >= 4.0
	List<Restaurant> findByCityIgnoreCaseAndIsOPenAndRatingGreaterThanEqual(
			String city, boolean isOpen, BigDecimal minRating);
	
	//sorting based on city and rating
	//ex. show me top rated restaurant in pune
	List<Restaurant> findByCityIgnoreCaseOrderByRatingDesc(String city);
	
	//@Query with JPQL - Search by name pattern
	// ex. Search bar "Find restaurant with 'pizza' in name"
	@Query("SELECT r FROM Restaurant r WHERE LOWER(r.name) LIKE LOWER(CONACAT('%', : keyword, '%'))")
	List<Restaurant> findByCities(@Param("keyword") String Keyword);
	
	//Query with JPQL - Multiple city filter
	// ex. show me restaurant in pune, Mumbai, or Delhi
	@Query("SELECT r FROM Restaurant r WHERE LOWER(r.city) IN :cities")
	List<Restaurant> findByCities(@Param("cities") List<String> cities);
	
	//Query - Count restaurant does this owner have
	//ex. how many restaurant does this owner have..?
	@Query("SELECT COUNT(r) FROM Restaurant r WHERE LOWER(r.ownerId = :ownerId)")
	Long countByOwnerId(@Param("ownerId") Long ownerId);
	
	//Query - Average rating across all restaurant
	//used for - Analytics dashboard
	@Query("SELECT AVG(r.rating) FROM Restaurant r")
	BigDecimal findAverageRating();
	
	//Query - Average rating by city
	//ex. "What is average rating in pune"
	@Query("SELECT COUNT(r) FROM Restaurant r WHERE LOWER(r.city) = LOWER(:city) AND LOWER(r.ciusioneType) = LOWER(:cuisione)")
	Long countByAndCuisione(@Param("city") String city, @Param("cuisione") String cuisione);
	
	@Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.menuItems WHERE r.id = :id")
	Optional<Restaurant> findbyIdWithMenuItems(@Param("id") Long id);
	
	
	//Query - Count of open vs closed restaurant
	// ex. Admin dashboard analytics
	@Query("SELECT r.isOpen, COUNT(r) FROM Restaurant r GROUPE BY r.isOpen ")
	List<Object[]> countOpenStates();
}
