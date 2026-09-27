package com.learning.springboot.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/*
 * The whole application starts here.
 * @SpringBootApplication is three annotations in one (topic01 prints the proof):
 *   @SpringBootConfiguration - this class is a @Configuration
 *   @EnableAutoConfiguration - let Boot configure beans based on the classpath
 *   @ComponentScan           - find @Component classes in THIS package and below,
 *                              which is why the main class sits in the root package
 * Run: ./mvnw spring-boot:run      then open http://localhost:8080/actuator
 */
@SpringBootApplication
@ConfigurationPropertiesScan          // registers every @ConfigurationProperties class (topic02)
public class BasicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicsApplication.class, args);
    }
}
