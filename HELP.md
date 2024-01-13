# CRUD API Project 

This project is a REST API for a book and author management system. The API provides CRUD (Create, Read, Update, Delete)
operations for managing books and authors.

## Features

- **Books API:** Allows you to create, read, update, and delete books.
- **Authors API:** Allows you to create, read, update, and delete authors.
- **Pagination:** Supports pagination for listing books and authors.
- **Nested Objects:** Supports mapping of nested objects using Model Mapper.

## Project Structure

````
.
├───src
   ├───main
   │   ├───java
   │   │   └───com
   │   │       └───tyrien
   │   │           └───quickstart
   │   │               ├───config
   │   │               ├───controllers
   │   │               ├───dao
   │   │               │   └───impl
   │   │               ├───domain
   │   │               │   ├───dataTransferObjects
   │   │               │   └───entities
   │   │               ├───mappers
   │   │               │   └───impl
   │   │               ├───repositories
   │   │               └───services
   │   │                   └───impl
   │   └───resources
   │       ├───static
   │       └───templates
   └───test
       ├───java
       │   └───com
       │       └───tyrien
       │           └───quickstart
       │               ├───controllers
       │               ├───dao
       │               │   └───impl
       │               └───repositories
       └───resources
````

## Endpoints

### Books API

- **Create Book:**
    - Method: PUT
    - URL: `/books/{ISBN}`
    - Response on Success: HTTP 201 Created
- **Read One Book:**
    - Method: GET
    - URL: `/books/{ISBN}`
    - Response on Success: HTTP 200 with the book as a JSON object
    - Response on Not Found: HTTP 404 with an empty response body
- **Read Many Books:**
    - Method: GET
    - URL: `/books`
    - Response: HTTP 200 with a list of books, even if it's empty
- **Update Book:**
    - Method: PUT
    - URL: `/books/{ISBN}`
    - Response: HTTP 200 for successful update (not HTTP 201)
- **Partial Update Book:**
    - Method: PATCH
    - URL: `/books/{ISBN}`
    - Response: HTTP 200 for successful partial update
- **Delete Book:**
    - Method: DELETE
    - URL: `/books/{ISBN}`
    - Response: HTTP 204 with no response body

### Authors API

- **Create Author:**
    - Method: POST
    - URL: `/authors`
    - Response on Success: HTTP 201 Created with the author object in the response body
- **Read One Author:**
    - Method: GET
    - URL: `/authors/{ID}`
    - Response on Success: HTTP 200 with the author object
    - Response on Not Found: HTTP 404 if the author is not found
- **Read Many Authors:**
    - Method: GET
    - URL: `/authors`
    - Response: HTTP 200 with a list of authors
- **Update Author:**
    - Full Update Method: PUT
    - URL: `/authors/{ID}`
    - Response: HTTP 200 for successful full update, replacing the existing author with the provided object
- **Partial Update Author:**
    - Method: PATCH
    - URL: `/authors/{ID}`
    - Response: HTTP 200 for successful partial update, updating only the provided attributes
- **Delete Author:**
    - Method: DELETE
    - URL: `/authors/{ID}`
    - Response: HTTP 204 with no response body; deletes the author in the database

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- ModelMapper
- JUnit
- MockMvc
- Postman

## Setup

To run this project, you need to have Java, a Java IDE, Docker and Postman installed on your machine.
The Spring Boot dependencies and PostgreSQL database can be installed/configured via your IDE.

1. Clone the repository to your local machine.
2. Update the `application.properties` file with your database credentials.
3. Start Docker Desktop (for windows) then run ```docker-up compose``` in your IDE terminal.
4. Run maven command `./mvnw clean verify` to ensure build is successful.
5. Run the application by using the command `./mvnw spring-boot:run` or by running the BooksAPIApplication class.
6. Use Postman or any other API client to interact with the endpoints.

## Testing

The project includes integration tests written using JUnit and MockMvc. To run the tests, use the command `./mvnw test`.
You can also run individual tests inside test classes or all the test of a single class via the IDE.

## Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#web)

## Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgements

Thanks to all contributors and users for your support. Your feedback is greatly appreciated.

Please note that this README is a work in progress. If you have any ideas on how to improve it, please feel free to
suggest changes. Thank you for contributing to this project!

**Notes:** When we use Spring JPA database setup via Hibernate, there is no need for the unit test files (
BookDaoImplTest &
AuthorDaoImplTest). Also, the following are no longer needed:

- dao package inside main.
- dao package inside test
- schema.sql file inside main package.

What we do is we create the new repositories directory inside the test folder in which we place the integration test
files by refactoring and moving them from the doa.impl package. I also change  <artifactId>
spring-boot-starter-jdbc</artifactId> inside the pom.xml file to  <artifactId>
spring-boot-starter-data-jpa