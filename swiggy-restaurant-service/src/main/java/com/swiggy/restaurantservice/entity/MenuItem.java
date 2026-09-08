package com.swiggy.restaurantservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//- Many MenuItems belong to ONE Restaurant (MANY-TO-ONE)
//- One Restaurant has MANY MenuItems (ONE-TO-MANY, defined on Restaurant side)

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	// THIS IS THE FOREIGN KEY - many items belong to one restaurant.
    // so a proper JPA relationship makes sense here (unlike ownerId above)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id", nullable = false, foreignKey = @ForeignKey( name ="fk_menu_item_restaurant"))
	@JsonBackReference
	private Restaurant restaurant;
	
	@Column(name = "name", nullable = false, length = 120)
	private String name;
	
	@Column(name = "description", length = 300)
	private String description;
	
	//Money should never be double/float - BigDecimal avoid rounding errors
	@Column(name = "price", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;
	
	@Column(name = "category", nullable = false, length = 50)
	private String category;      //like STARTER, MAIN_COURSE, DESERT, BEVERAGE
	
	@Column(name = "is_available", nullable = false)
	private boolean isAvailable = true;    // Out of stock items can be marked unavailable

	
	@Column(name = "image_url", length = 500)
	private String imageUrl;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false, updatable = false)
	private LocalDateTime updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
