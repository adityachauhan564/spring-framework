package com.learning.springboot.basics.topic03_profiles;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class ProfilesRunner implements ApplicationRunner {

    private final Environment environment;
    private final PaymentGateway paymentGateway;
    private final String environmentLabel;

    public ProfilesRunner(Environment environment, PaymentGateway paymentGateway,
                          @Value("${app.environment-label}") String environmentLabel) {
        this.environment = environment;
        this.paymentGateway = paymentGateway;
        this.environmentLabel = environmentLabel;
    }

    @Override
    public void run(ApplicationArguments args) {
        String[] active = environment.getActiveProfiles();
        System.out.println("\n=== topic03: profiles ===");
        System.out.println("active profiles: " + (active.length == 0 ? "none -> 'default'" : Arrays.toString(active)));
        System.out.println("app.environment-label = " + environmentLabel + "   (overridden by application-<profile>.yml)");
        System.out.println("PaymentGateway bean   = " + paymentGateway.describe());
    }
}
