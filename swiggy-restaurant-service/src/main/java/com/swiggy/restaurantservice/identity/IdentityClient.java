package com.swiggy.restaurantservice.identity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


//Boundary class to communicate with Identity Service for user authentication and authorization
@Component
public class IdentityClient {
	private final RestClient restClient ; // RestClient is a Spring class to make REST calls to other services
	
	// Constructor injection of base URL for Identity Service from application properties
	//
	public IdentityClient(@Value("${identity-service.base-url}") String baseUrl) {
		this.restClient = RestClient.builder().baseUrl(baseUrl).build();
	}
}
