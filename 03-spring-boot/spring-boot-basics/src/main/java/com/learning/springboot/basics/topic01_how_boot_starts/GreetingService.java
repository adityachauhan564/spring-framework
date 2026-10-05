package com.learning.springboot.basics.topic01_how_boot_starts;

/*
 * Conditional beans - the same trick that auto-configuration uses, now in your own code.
 * Only ONE of the two implementations is created, chosen by the property app.greeting.style:
 *   friendly (default, also when the property is missing) -> FriendlyGreeting
 *   formal                                                -> FormalGreeting
 * Try this : ./mvnw spring-boot:run -Dspring-boot.run.arguments=--app.greeting.style=formal
 */
public interface GreetingService {

    String greet(String name);
}
