package com.springcore.topic11_java_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * A second configuration class. In big apps you split config by area
 * (web, data, security...) and join the parts together with @Import.
 * Like chapters of one book: each chapter is separate, the index (@Import) links them.
 * Here the dependency comes in as a METHOD PARAMETER - Spring passes the Car bean in for you.
 */
@Configuration
public class DealershipConfig {

    @Bean
    public Dealership dealership(Car car) {
        return new Dealership(car);
    }
}
