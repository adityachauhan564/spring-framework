package com.springcore.topic11_java_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * A second configuration class. Split config by area (web, data, security...) and
 * combine the parts with @Import.
 * Here the dependency arrives as a METHOD PARAMETER - Spring passes the Car bean in.
 */
@Configuration
public class DealershipConfig {

    @Bean
    public Dealership dealership(Car car) {
        return new Dealership(car);
    }
}
