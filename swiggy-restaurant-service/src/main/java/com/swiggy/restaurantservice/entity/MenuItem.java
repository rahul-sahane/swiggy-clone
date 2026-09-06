package com.swiggy.restaurantservice.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	// Real foreign key - MenuItem and Restaurant live in the SAME database,
    // so a proper JPA relationship makes sense here (unlike ownerId above)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;
	
	@Column(name = "name", nullable = false, length = 150)
	private String name;
	
	@Column(name = "description", length = 500)
	private String description;
	
	//Money should never be double/float - BigDecimal avoid rounding errors
	@Column(name = "price", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;
	
	@Column(name = "category", nullable = false, length = 50)
	private String category;      //like STARTER, MAIN_COURSE, DESERT, BEVERAGE
	
	@Column(name = "is_available", nullable = false)
	private boolean isAvailable = true;
}
