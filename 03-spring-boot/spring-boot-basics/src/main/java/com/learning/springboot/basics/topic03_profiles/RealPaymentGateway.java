package com.learning.springboot.basics.topic03_profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/* Used only in production. (Just a stand-in: it doesn't really call a payment provider.) */
@Component
@Profile("prod")
public class RealPaymentGateway implements PaymentGateway {

    @Override
    public String describe() {
        return "real gateway - charges customers";
    }
}
