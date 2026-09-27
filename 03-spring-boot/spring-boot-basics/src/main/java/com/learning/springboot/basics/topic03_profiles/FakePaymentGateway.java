package com.learning.springboot.basics.topic03_profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/* "!prod" = every profile EXCEPT prod, including "default" and "dev": safe for development. */
@Component
@Profile("!prod")
public class FakePaymentGateway implements PaymentGateway {

    @Override
    public String describe() {
        return "fake gateway - no real money moves";
    }
}
