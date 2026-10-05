package com.learning.springboot.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/*
 * The whole application starts here.
 * @SpringBootApplication is three annotations in one (topic01 prints the proof):
 *   @SpringBootConfiguration - this class is a @Configuration
 *   @EnableAutoConfiguration - let Boot create beans by itself, based on the libraries on the classpath
 *   @ComponentScan           - find @Component classes in THIS package and in every package below it.
 *                              That is why the main class sits in the top (root) package.
 * Run: ./mvnw spring-boot:run      then open http://localhost:8080/actuator
 */
@SpringBootApplication
@ConfigurationPropertiesScan          // finds and registers every @ConfigurationProperties class (topic02)
public class BasicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicsApplication.class, args);
    }
}
