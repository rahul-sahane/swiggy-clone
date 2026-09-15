package com.swiggy.restaurantservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.swiggy.restaurantservice.entity.MenuItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Response DTO - what we send to client
//why separate from entity - because we don't want to send the entire entity to the client, 
//we want to send only the required fields

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder        //Enable MenuItenResponse.bulider().name("Pizza").price(...).build() to create object
public class MenuItemResponse {
	
	private String id;
	private Long restaurantId; // just id , not entire restaurant object
	private String restaurantName; 
	private String name;  //
	private String description;
	private BigDecimal price;
	private String catagory;
	private boolean isVagetarian;
	private boolean isAvailable;
	private String imageUrl;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	//factory method : Convert MenuItem entity to MenuItemResponse DTO 
	public static MenuItemResponse fromEntity(MenuItem menuItem) {
		return MenuItemResponse.builder()
		.id(menuItem.getId())
		.restuarantId(menuItem.getRestaurant().getId())
		.restaurantName(menuItem.getRestaurant().getName())
		.name(menuItem.getName())
		.description(menuItem.getDescription())
		.price(menuItem.getPrice())
		.category(menuItem.getCategory())
		.isVagetarian(menuItem.isVegetarian())
		.isAvailable(menuItem.isAvailable())
		.imageUrl(menuItem.getImageUrl())
		.createdAt(menuItem.getCreatedAt())
		.updatedAt(menuItem.getUpdatedAt())
		.build();
	}
}
