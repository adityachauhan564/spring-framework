package com.learning.springboot.basics.topic03_profiles;

/*
 * Topic    : Profiles - one build, different behaviour per environment
 * Key idea : The same jar behaves differently in each environment (dev, test, prod).
 *            - spring.profiles.active=prod makes Boot ALSO load application-prod.yml
 *              (its values win over application.yml).
 *            - Boot creates only the beans whose @Profile matches.
 *            - If no profile is active, Boot uses the profile called "default".
 *            - Like a practice match vs the real match: same team, but in practice you
 *              use a tennis ball (fake payment), in the real match a proper one.
 * Try this : ./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
 */
public interface PaymentGateway {

    String describe();
}
