package com.learning.springboot.basics.topic03_profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/* "!prod" means every profile EXCEPT prod, including "default" and "dev". Safe to use while developing. */
@Component
@Profile("!prod")
public class FakePaymentGateway implements PaymentGateway {

    @Override
    public String describe() {
        return "fake gateway - no real money moves";
    }
}
