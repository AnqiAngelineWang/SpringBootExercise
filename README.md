# SpringBootExercise

Write an API microservice using Spring Boot to simulate user registration. Write and push your code to Github so the interviewer can see the development process.

- Expose REST API to accept a payload of username, password, and IP address.
(star the password while user inputs on UI. Generate the eye icon to show password)
- All parameters must not be blank (!= empty and null). Return error messages if not valid

- Password need to be greater than 8 characters, containing at least 1 number, 1 Capitalized letter, 1 special character in this set “_ # $ % .” Return error messages if not valid

- Call this end point to get geolocation for the provided IP: IP-API.com - Geolocation API - Documentation - JSON. If the IP is not in Canada, return error message that user is not eligible to register
- When all validation is passed, return a random uuid and a welcome message with his username and his City Name (resolved using ip-geolocation api)
- The API need to have OpenAPI specification, no matter what your approach is code first or design first.

- Project must use Maven or Gradle to build. Generate a spring boot project here: Spring Initializr
(resourse.application.properties need to add username and password to access for : #http://localhost:8080/actuator/beans
#http://localhost:8080/actuator/mappings
)

- Need to have JUnit Tests

**************************************** Play with these steps ********************************************************************
Clone the project, start the application **ThymeleafdemoApplication.java**, go to the link, and follow the instruction.
http://localhost:8080/showForm

The code is on **master** branch 
