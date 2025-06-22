# Hostfully

## Configuring the application

Before the application can be executed the database needs to be configured: 
   * Go to the `applications.properties` file.
   * Update the properties below with your PostgreSQL configuration:
   > spring.datasource.url=jdbc:postgresql://localhost:5432/<YOUR_DATABASE_NAME>
   > spring.datasource.username=<YOUR_USERNAME>
   > spring.datasource.password=<YOUR_PASSWORD>


## Running the application locally

First execute the clean install command from Maven
   * Execute the command `mvn clean install`

After that the application can be executed, there are several ways to execute application on your local machine.
   * One way is to execute the `main` method in the `com.recime.recipes.RecipesApplication` class from your IDE or terminal.
   * Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) with the command mvn spring-boot:run

## API Documentation

Swagger API Documentation can be found in the properties folder and can be viewed on the [Swagger Editor](https://editor.swagger.io/)

## Design Choices

Below is the design choices for this project and the reasoning behind them:
   * Layered Architecture
   > The project is splited in 4 basic layers, being: i) DTO Layers; ii) Controller Layer; iii) Service Layer; iv) Repository Layer. Having this approach we can achieve a good separation of concerns, each layer has a single responsability, this also allow the project to have better and more accurate tests and a more flexible evolution.
   >
   > Below is a table with the description of each layer and the reasoning behind it:

|Layer|Description|Reason|
|---|---|---|
| DTO Layer | Defines the model exposed | Avoids exposure of internal information; Allows version on the API; Allows the application to expose a better and more flexible API contract. |
| Controller Layer | Handle the HTTP requests | Unique entry point that prevents duplication; Centralizes request validation, parsing, error handling, etc. |
| Service Layer | Contains all the business logic | Encapsulates business rule; Enables better tests; Prevents business logic duplication. |
| Repository Layer | Hadles database access | Removes the database access logic from the business logic; allow for moking the database access for testing; enables easier database switch |

   * Idempotency Handling

   > The API implements Idempotency-Key support for POSToperations, ensuring  that duplicate client requests do not result in duplicated resource creation. This is achieved through a custom Spring HandlerInterceptor and response wrapper that captures and stores the final response (HTTP status and body) against a unique Idempotency-Key provided in the request header.
   >
   > If the same Idempotency-Key is reused, the API will return the cached response from the first request with the exact same HTTP status and body, guaranteeing consistent behavior.

   * Unit Test
   > The project follows a unit testing approach focused on isolating business logic and mocking external dependencies such as repositories and external services. This allows for fast, deterministic, and lightweight tests that validate the behavior of each service and component in isolation.
   > 
   > In this project was used JUnit and Mockito. Unit tests cover all service-layer logic, including input validation, error scenarios, void method interactions, and mapper conversions.

   * Integration Testing
   > For integration testing, the project uses Spring Boot Test support to validate end-to-end behavior, including controller endpoints, full dependency injection, request validation, and real repository interactions with PostgreSQL.
   >
   > Integration tests focus on verifying that HTTP endpoints behave correctly, with proper status codes, request/response payload validation, input validation errors, and persistence layer interactions. This testing strategy helps ensure that the entire application stack works cohesively, from the web layer down to the database.



   
