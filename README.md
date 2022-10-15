# User Service Based Api

# Features
- User Module

### Technologies

User Service  api uses :

* Spring Boot
* Spring Data Jpa
* MySQL 
* H2 for testing database
* Swagger


### Without Docker Installation

1. git clone https://github.com/shouvick/user-service-api.git
2. Create one mysql database with name "userservicedb" and in application.yml configure the db username and password.
3. Run the project command **mvn clean install**
4. Check http://localhost:8080/swagger-ui/index.html for api documentation

###  Docker Installation

1. git clone https://github.com/shouvick/user-service-api.git
2. Run **mvn clean install** to build & verify all test case passed.
3. Run **docker-compose up** to build dockerized application.
4. Check http://localhost:8080/swagger-ui/index.html for api documentation


### TDD instruction
Unit Test & Integration Test also added.


### API Instructions for Swagger
1. **saveUser** for creating new user on response userId.
2. **saveUserLocation** with userId from 1 and location.
3. **getUserById** get user latest locations.
4. **getUserByDateTimeRange** get user locations between date range.

### Developer Info
* MD.Shohag Monzur Shouvick
* Version : 1.1.0
* Created At : 16.10.2022