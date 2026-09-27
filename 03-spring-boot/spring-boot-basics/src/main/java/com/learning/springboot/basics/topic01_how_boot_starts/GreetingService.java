package com.learning.springboot.basics.topic01_how_boot_starts;

/*
 * Conditional beans - the mechanism behind auto-configuration, used in your own code.
 * Exactly one implementation exists, chosen by the property app.greeting.style:
 *   friendly (default, also when the property is missing) -> FriendlyGreeting
 *   formal                                                -> FormalGreeting
 * Try this : ./mvnw spring-boot:run -Dspring-boot.run.arguments=--app.greeting.style=formal
 */
public interface GreetingService {

    String greet(String name);
}
