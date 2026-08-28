# swiggy-clone
Swiggy 

A food-delivery backend inspired by Swiggy, built using Java, Spring Boot, and Microservices Architecture.

Project Status: Work in Progress This project is currently under active development. Services are being built and added incrementally. Not production-ready yet.

Tech Stack (planned)
Backend: Java 17/21, Spring Boot
Architecture: Microservices, REST APIs
Database: Oracle
Caching: Redis
Messaging: Apache Kafka
Service Discovery: Eureka
API Gateway: Spring Cloud Gateway
Security: Spring Security, JWT
Build Tool: Maven

Planned Microservices

Service------------------------------Status---------------------	Description
swiggy-user-service	                 In Progress	                Handles signup, login, user profiles
swiggy-restaurant-service	         ⏳ Not Started             	Restaurant & menu management
swiggy-order-service	             ⏳ Not                       Started	Order placement & lifecycle
swiggy-delivery-matching-service	 ⏳ Not                       Started	Matches nearest delivery partner (Redis Geo)
swiggy-payment-service	             ⏳ Not                       Started	Payment processing
swiggy-notification-service	         ⏳ Not                       Started	Kafka-based notifications
swiggy-api-gateway	                 ⏳ Not                       Started	Single entry point for all services
swiggy-eureka-server	             ⏳ Not                       Started	Service registry


Progress Log
    Project structure planned
    swiggy-user-service scaffolded (User entity, repository)
    Building signup/login APIs
    
How to Run
Instructions will be added once the first service is complete.
This README will be updated as the project progresses.

