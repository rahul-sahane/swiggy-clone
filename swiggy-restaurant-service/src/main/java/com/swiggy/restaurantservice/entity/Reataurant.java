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
public class Reataurant {
	
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
	
	@Column(name = "cuisine_type", length = 100)
	private String cuisineType;
	
	@Column(name = "rating")
	private double rating = 0.0;
	
	@Column(name = "is_open", nullable = false)
	private boolean isopen = true;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
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
