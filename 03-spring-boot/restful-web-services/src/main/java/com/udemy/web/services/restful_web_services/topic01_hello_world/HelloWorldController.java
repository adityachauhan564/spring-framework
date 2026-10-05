package com.udemy.web.services.restful_web_services.topic01_hello_world;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Topic    : Your first REST endpoints
 * Key idea : @RestController methods return DATA, not pages.
 *            - A String is sent as plain text.
 *            - An object (record) is turned into JSON by Jackson. No configuration needed:
 *              Spring Boot set it all up (compare 02-spring-foundations/spring-mvc topic07).
 *            - Like a vending machine: press a button (URL), get the item (data) - no waiter, no menu card.
 * Try this : curl localhost:8080/hello-world
 *            curl localhost:8080/hello-world-bean
 */
@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }
}
