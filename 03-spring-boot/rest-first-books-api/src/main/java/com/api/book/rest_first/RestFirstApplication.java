package com.api.book.rest_first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Capstone: a complete Books REST API, using everything from this stage.
 * Like a small library counter: you ask for books, add books, return books.
 * Read the layers in this order:
 *   entities.Book            - the table
 *   dao.BookRepository       - talks to the database (Spring Data JPA)
 *   dto.BookRequest/Response - what the API receives and what it sends back
 *   services.BookService     - business logic + transactions
 *   controllers.BookController - HTTP
 *   exceptions.*             - one place for error responses
 * Run: ./mvnw spring-boot:run   then  curl localhost:8080/books   or open /swagger-ui.html
 */
@SpringBootApplication
public class RestFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestFirstApplication.class, args);
    }
}
