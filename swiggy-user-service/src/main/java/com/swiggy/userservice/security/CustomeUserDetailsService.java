package com.swiggy.userservice.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swiggy.userservice.entity.User;
import com.swiggy.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

//Spring Security doesn't know how to fetch user from OUR "user" table by itself
//this class is is the bridge that teaches it how

@Service
@RequiredArgsConstructor
public class CustomeUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException{
		
		// In this application "username" is actually the email - we don't have a seperate username field
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		
		// Spring Security needs a userDetails Obj, not our own User Entity
		// org.springframework.security.core.userdetails.User is a built-in implementation
		return  org.springframework.security.core.userdetails.User.builder()
				.username(user.getEmail())
				.password(user.getPassword())   //already hashed - Spring security compares hashes
				.authorities(List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())))
				.disabled(!user.isActive())
				.build();						//inactive account can't log in
	}
}
