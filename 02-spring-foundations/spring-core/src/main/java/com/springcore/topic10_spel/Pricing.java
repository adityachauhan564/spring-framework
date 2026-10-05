package com.springcore.topic10_spel;

import java.util.List;

import org.springframework.stereotype.Component;

/* A simple bean named "pricing". The SpEL expressions in SpelExamples read its values using that name. */
@Component
public class Pricing {

    public double getBasePrice() {
        return 1000;
    }

    public List<Integer> getPrices() {
        return List.of(50, 120, 300, 80, 999);
    }
}
