package com.swiggy.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiggy.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	// Use to check "does this email already exist?" before signup
	boolean existsByEmail(String email);
}
