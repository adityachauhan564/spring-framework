package com.springcore.topic10_spel;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * SpEL (Spring Expression Language): #{ ... } is CALCULATED when the bean is created.
 * Don't mix it up with ${ ... }. That one only looks up a value from a properties file (topic12).
 * Easy way to remember: # = calculate, $ = look up.
 */
@Component
public class SpelExamples {

    @Value("#{22 + 11}")                                   // maths
    private int sum;

    @Value("#{T(java.lang.Math).sqrt(25)}")                // T(...) means "this class", so you can call its static methods
    private double squareRoot;

    @Value("#{8 > 3 and 2 > 5}")                           // logic (true and false = false)
    private boolean bothTrue;

    @Value("#{'hello spring'.toUpperCase()}")              // calling a method on a fixed String
    private String shout;

    @Value("#{pricing.basePrice * 1.18}")                  // reads another bean's value by its bean name (+18% GST)
    private double priceWithTax;

    @Value("#{pricing.basePrice > 500 ? 'premium' : 'basic'}")   // ternary: if-else in one line
    private String tier;

    @Value("#{pricing.prices.?[#this > 100]}")             // selection: keeps only the elements that match
    private List<Integer> expensivePrices;

    @Value("#{systemProperties['user.name'] ?: 'unknown'}")     // Elvis operator ?: gives a default value when it is null
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
