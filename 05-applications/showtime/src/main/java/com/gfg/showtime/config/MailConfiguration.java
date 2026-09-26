package com.gfg.showtime.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// JavaMailSender is auto-configured by Spring Boot from the spring.mail.* properties.
@Configuration
public class MailConfiguration {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

}
