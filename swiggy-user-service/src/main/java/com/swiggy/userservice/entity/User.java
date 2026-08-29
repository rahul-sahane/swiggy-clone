package com.swiggy.userservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 					   // Tells Spring: "this class = a database table"
@Table(name = "users")		  //  The actual table name in Oracle 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "full_name", nullable = false, length = 100)
	private String fullName;
	
	@Column(name = "email", nullable = false, length = 100)
	private String email;
	
	@Column(name = "password_hash", nullable = false, length = 255)
	private String password;
	
	@Column(name = "phone_number", nullable = false, length = 20)
	private String phoneNumber;
	
	@Column(name = "role", nullable = false, length = 30)
	private String role;
	
	@Column(name = "is_active", nullable = false)
	private boolean isActive;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	LocalDateTime updatedAt;
	
	
	
	 // These two run AUTOMATICALLY before saving/updating
	@PrePersist									// Runs just BEFORE a new row is inserted
	protected void OnCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	@PreUpdate									// Runs just BEFORE an existing row is updated
	protected void OnUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
