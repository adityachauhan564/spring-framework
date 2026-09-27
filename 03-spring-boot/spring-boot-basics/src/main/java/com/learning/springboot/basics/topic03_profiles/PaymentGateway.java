package com.learning.springboot.basics.topic03_profiles;

/*
 * Topic    : Profiles - one build, different behaviour per environment
 * Key idea : spring.profiles.active=prod makes Boot ALSO load application-prod.yml (its values
 *            override application.yml) and create only the beans whose @Profile matches.
 *            Nothing active -> the profile called "default".
 * Try this : ./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
 */
public interface PaymentGateway {

    String describe();
}
