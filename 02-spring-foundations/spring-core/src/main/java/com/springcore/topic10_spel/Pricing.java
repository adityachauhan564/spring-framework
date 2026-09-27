package com.springcore.topic10_spel;

import java.util.List;

import org.springframework.stereotype.Component;

/* A bean whose properties the SpEL expressions in SpelExamples read, by bean name "pricing". */
@Component
public class Pricing {

    public double getBasePrice() {
        return 1000;
    }

    public List<Integer> getPrices() {
        return List.of(50, 120, 300, 80, 999);
    }
}
