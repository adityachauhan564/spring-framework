package com.springcore.topic10_spel;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * SpEL (Spring Expression Language): #{ ... } is EVALUATED when the bean is created.
 * Don't mix it up with ${ ... }, which only looks up a property (topic12).
 */
@Component
public class SpelExamples {

    @Value("#{22 + 11}")                                   // arithmetic
    private int sum;

    @Value("#{T(java.lang.Math).sqrt(25)}")                // T(...) = a class, to call static methods
    private double squareRoot;

    @Value("#{8 > 3 and 2 > 5}")                           // logic
    private boolean bothTrue;

    @Value("#{'hello spring'.toUpperCase()}")              // method call on a literal
    private String shout;

    @Value("#{pricing.basePrice * 1.18}")                  // another bean's property, by bean name
    private double priceWithTax;

    @Value("#{pricing.basePrice > 500 ? 'premium' : 'basic'}")   // ternary
    private String tier;

    @Value("#{pricing.prices.?[#this > 100]}")             // selection: keep matching elements
    private List<Integer> expensivePrices;

    @Value("#{systemProperties['user.name'] ?: 'unknown'}")     // Elvis: default when null
    private String currentUser;

    @Override
    public String toString() {
        return String.join("\n",
                "  #{22 + 11}                      = " + sum,
                "  #{T(Math).sqrt(25)}             = " + squareRoot,
                "  #{8 > 3 and 2 > 5}              = " + bothTrue,
                "  #{'hello spring'.toUpperCase()} = " + shout,
                "  #{pricing.basePrice * 1.18}     = " + priceWithTax,
                "  #{... ? 'premium' : 'basic'}    = " + tier,
                "  #{pricing.prices.?[#this > 100]} = " + expensivePrices,
                "  #{systemProperties[...] ?: ...} = " + currentUser);
    }
}
