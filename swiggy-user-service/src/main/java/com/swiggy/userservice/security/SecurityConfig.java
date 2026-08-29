package com.swiggy.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

//This is the "master control panel" for security in service
//This wires everything we've built so far :
// - JwtAuthfilter       (checks token on incoming requests)
// - CustomUserDetailsService (load user from our DB)
// - PasswordEncoder     (hashes/verifies passwords)
// - AuthenticationManager   (used during login to check credentials )
// It also check whisvh URLs are public (signup/login) vs protected 

@Configuration
@EnableWebSecurity                    //Turns on Spring security's
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CustomUserDetailsService userDetailsService;
	private final JwtAuthFilter jwtAuthFilter;
	
	//Bean 1: PasswordEncoder
	//Used to password during signup, and to verify them during login
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Bean 2: AuthenticationProvider
	// Tells Spring Security Hoe to authenticate : use OUR CustomeUserDetailsService
	//to fetch the user, and Our PasswordEncoder to check the password
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider= new DaoAuthenticationProvider(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	//Bean 3: AuthenticationManager
	//this is what UserServiceImpl.login() actually calls to verify credentials.
	//Spring Boot Builds this for us from the AuthenticationConfiguration.
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		
		return config.getAuthenticationManager();
	}
	
	//Bean 4: The actual security rules - which URLs are public , whisch need a token
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
		
			 //here Disabling CSRF protection is for browser sessio-based apps using cookies
			// we are using stateless JWT tokens instead, so SCRF dosen't apply here.
			.csrf(csrf -> csrf.disable())
			
			//Define access rule for different URL patterns
			.authorizeHttpRequests(auth -> auth
					// Signup and login must be PUBLIC 
					.requestMatchers("/api/auth/signup", "/api/auth/login").permitAll()
					//Everything alse requires a valid JWT token
					.anyRequest().authenticated()
				)
			
			  // Tell Spring Security: don't create HTTP sessions.
             // Every request must prove who it is via the JWT token, every single time.
			.sessionManagement(session -> session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				)
			
			 // Register our JWT filter to run BEFORE Spring's default username/password filter
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
}
