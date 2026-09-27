package com.learning.springboot.basics.topic01_how_boot_starts;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.greeting.style", havingValue = "friendly", matchIfMissing = true)
public class FriendlyGreeting implements GreetingService {

    @Override
    public String greet(String name) {
        return "Hey " + name + "!";
    }
}
