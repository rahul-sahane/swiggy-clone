package com.swiggy.restaurantservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	//Owner ID references a user in the User Service.
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
	private BigDecimal  rating = BigDecimal.ZERO;
	
	
	//Is the kitchen currently accepting orders?
	@Column(name = "is_open", nullable = false)
	private boolean isopen = true;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	
	// ONE-TO-MANY relationship : one restaurant has many MenuItems
	//orphanRemoval = true means: remove item from this list
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference //prevent infinite JSON loop with MEnuItem's
	private List<MenuItem> menuItems = new ArrayList<>();
	
	//Helper method to maintain both side of the relation
	public void addMenuItem(MenuItem item) {
		menuItems.add(item);
		item.setRestaurant(this);
	}
	
	public void RemoveMenuItem(MenuItem item) {
		menuItems.remove(item);
		item.setRestaurant(null);
	}
	
	
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
