package com.swiggy.restaurantservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	// References a user (role = RESTAURANT_OWNER) in swiggy-user-service.
	@Column(name = "owner_id", nullable = false)
	private Long ownerId;
	
	@Column(name = "name", nullable = false, length = 150)
	private String name;
	
	@Column(name = "address", nullable = false, length = 250)
	private String address;
	
	@Column(name = "city", nullable = false, length =100)
	private String city;
	
	
	// GPS coordinates for distance-based search.
	//Precision: 7 decimal places = ~1cm accuracy (more than enough).
	@Column(name = "latitude", nullable = false, precision = 10, scale = 7)
    private Double latitude;

    @Column(name = "longitude", nullable = false, precision = 10, scale = 7)
    private Double longitude;
    
	@Column(name = "cuisine_type", length = 100)
	private String cuisineType;
	
	
	//Average rating calculated from reviews
	@Column(name = "rating",nullable = false, precision = 3, scale = 2)
	private double rating = 0.0;
	
	
	//Is the kitchen currently accepting orders?
	@Column(name = "is_open", nullable = false)
	private boolean isopen = true;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	
	//JPA Lifecycle Callbacks - Run automatically before save/update.
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	@PrePersist
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
