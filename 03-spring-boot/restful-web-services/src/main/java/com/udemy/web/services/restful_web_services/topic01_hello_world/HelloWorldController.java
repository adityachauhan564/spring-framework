package com.udemy.web.services.restful_web_services.topic01_hello_world;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Topic    : Your first REST endpoints
 * Key idea : @RestController methods return DATA. A String is sent as plain text;
 *            an object (record) is converted to JSON by Jackson - no configuration needed,
 *            Spring Boot set it all up (compare 02-spring-foundations/spring-mvc topic07).
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
