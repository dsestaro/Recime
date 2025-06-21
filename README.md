# Hostfully

## Configuring the application

Before the application can be executed the database needs to be configured: 
   * Go to the `applications.properties` file.
   * Update the properties below with your PostgreSQL configuration:
   > spring.datasource.url=jdbc:postgresql://localhost:5432/<YOUR_DATABASE_NAME>
   > spring.datasource.username=<YOUR_USERNAME>
   > spring.datasource.password=<YOUR_PASSWORD>


## Running the application locally

There are several ways to execute application on your local machine.
   * One way is to execute the `main` method in the `com.recime.recipes.RecipesApplication` class from your IDE or terminal.
   * Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) with the command mvn spring-boot:run

## API Documentation

Swagger API Documentation can be found in the properties folder and can be viewed on the [Swagger Editor](https://editor.swagger.io/)