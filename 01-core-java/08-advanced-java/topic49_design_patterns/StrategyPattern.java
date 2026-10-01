package topic49_design_patterns;

import java.util.List;

/*
 * Pattern  : Strategy - swap the rule (algorithm) without touching the code that uses it
 * Use for  : pricing rules, sorting orders, payment methods, validation rules.
 * Key idea : The part that changes becomes an interface. The class that uses it is given one from outside.
 *            If the interface has just one method, a strategy can simply be a lambda.
 *            Like a shop's billing counter: same counter, but on Diwali it uses the "festive sale"
 *            pricing rule, and for members the "member discount" rule.
 * Spring   : Injecting a different implementation of an interface - that's Strategy + DI (topic 14).
 * Run      : java -cp out topic49_design_patterns.StrategyPattern
 */
public class StrategyPattern {

    @FunctionalInterface
    interface PricingStrategy {
        double priceFor(double basePrice);
    }

    static class Checkout {
        private final PricingStrategy pricing;

        Checkout(PricingStrategy pricing) {           // the rule is handed in from outside (injected)
            this.pricing = pricing;
        }

        double total(List<Double> items) {
            double sum = 0;
            for (double item : items) {
                sum += pricing.priceFor(item);         // Checkout never knows WHICH rule it is using
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        List<Double> cart = List.of(100.0, 250.0);

        PricingStrategy regular = price -> price;                     // full price
        PricingStrategy festiveSale = price -> price * 0.8;           // 20% off
        PricingStrategy member = price -> Math.max(0, price - 30);    // flat 30 off each item, never below 0

        System.out.println("regular: " + new Checkout(regular).total(cart));
        System.out.println("festive: " + new Checkout(festiveSale).total(cart));
        System.out.println("member:  " + new Checkout(member).total(cart));
    }
}
