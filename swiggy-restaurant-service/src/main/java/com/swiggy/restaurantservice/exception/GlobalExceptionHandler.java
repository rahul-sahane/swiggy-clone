package com.swiggy.restaurantservice.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice tells Spring : "Watch every @RestController in this application.
// If any of them throw an exception, send it to me for handling"

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private Map<String, Object> body(HttpStatus status, String message){
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());
		body.put("message", message);
		return body;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handlerValidationErrors(MethodArgumentNotValidException ex){
		
		//collect Every invalid field one map, instead of only
		//reporting the first one found.
		Map<String, String> fieldErrors = new HashMap<>();
		for(var error : ex.getBindingResult().getFieldErrors()) {
			fieldErrors.put(error.getField(), error.getDefaultMessage());
		}
		
		Map<String, Object> response = body(HttpStatus.BAD_REQUEST, "Validation failed");
		response.put("Error", fieldErrors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	
	//fire when RestaurantServiceImpl throws this a requested 
	//restaurant id doesn't exit in the database.
	@ExceptionHandler(RestaurantNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handlerNotFound(RestaurantNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body(HttpStatus.CONFLICT, ex.getMessage()));
	}
	
	//fires when someone tries to delete a restaurant that still has menu item on it.
	//and also 409 conflict
	@ExceptionHandler(DublicateRestaurantException.class)
	public ResponseEntity<Map<String, Object>> handleRestuarantNotEmpty(RestaurantNotFoundException ex){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body(HttpStatus.UNAUTHORIZED, ex.getMessage()));
	}
}
