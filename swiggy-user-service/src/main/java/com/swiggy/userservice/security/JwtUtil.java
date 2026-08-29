package com.swiggy.userservice.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")				// Read from application.properties -> jwt.secret
	private String secretKey;
	
	@Value("${jwt.expiration-ms}")		// Read from application.properties -> i.e jwt.expiration-ms=86400000 -> 24h
	private Long expirationMs;
	
	 // Converts our plain-text secret string into a proper cryptographic key object
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
		
	}
	
	//1. Generate token for a given user's email
	public String generateToken(String email) {
		return Jwts.builder()					
				.subject(email)					//"who" this token belongs to
				.issuedAt(new Date())			//when it was created
				.expiration(new Date (System.currentTimeMillis() + expirationMs)) //when it expires
				.signWith(getSigningKey(), SignatureAlgorithm.HS256) //sign in, so i can't be faked
				.compact();      									 // turn it into the final token String
				
	}
	
	//2. Extract the email(subject) out of a token
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	//Generic helper to pull any piece of info out of the token's claims
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return claimsResolver.apply(claims);
	}
	
	//3. Checking if a token is valid for a given email AND not expired
	public boolean isTokenValid(String token, String email) {
		final String tokenEmail = extractEmail(token);
		return (tokenEmail.equals(email) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		Date expiration = extractClaim(token, Claims::getExpiration);
		return expiration.before(new Date());
		
	}
}
