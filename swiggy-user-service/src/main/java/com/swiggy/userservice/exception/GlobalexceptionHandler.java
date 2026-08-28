package com.swiggy.userservice.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//This class catches errors thrown anywhere in our controller
//and turn them into JSON response instead of ugly stack traces.

@RestControllerAdvice
public class GlobalexceptionHandler {
	

	//runs when @Valid finds a problem (e.g, missing email, short pass)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handlerValidationError(MethodArgumentNotValidException ex){
		
		//collect all field error into a simple map e.g. {"email" : "Invalid email formate"}
		Map<String, String> fieldErrors = new HashMap<>();
		for (var error : ex.getBindingResult().getFieldErrors()) {
			fieldErrors.put(error.getField(), error.getDefaultMessage());
		}
		
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("message", "Validation failed");
		body.put("errors", fieldErrors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		
	}
	
	// Runs when someone signs up with an email that's already taken
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleUserAlreadyExists(UserAlreadyExistsException ex){
    	
    	Map<String, Object> body = new HashMap<>();
    	body.put("timestamp", LocalDateTime.now());
    	body.put("status", HttpStatus.CONFLICT.value());
    	body.put("message", ex.getMessage());
    	
    	return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
    
    //Run when login email/pass don't match
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentials(BadCredentialsException ex){
    	
    	Map<String, Object> body = new HashMap<>();
    	body.put("timestamp", LocalDateTime.now());
    	body.put("status", HttpStatus.UNAUTHORIZED.value());
    	body.put("message", "Invalid email or Password");
    	
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }
    
    //Catch anything else we didn't except, so the client never sees a raw stack trace
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOtherError(Exception ex){
    	
    	Map<String, Object> body = new HashMap<>();
    	body.put("timestamp", LocalDateTime.now());
    	body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    	body.put("message", "Something went wrong");
    	
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
