package com.learning.springboot.basics.topic01_how_boot_starts;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class GreetingRunner implements ApplicationRunner {

    private final GreetingService greetingService;

    public GreetingRunner(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Conditional bean chosen by app.greeting.style: "
                + greetingService.getClass().getSimpleName() + " -> " + greetingService.greet("Asha"));
    }
}
