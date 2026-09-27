package com.learning.springboot.basics.topic01_how_boot_starts;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.greeting.style", havingValue = "formal")
public class FormalGreeting implements GreetingService {

    @Override
    public String greet(String name) {
        return "Good day, " + name + ".";
    }
}
