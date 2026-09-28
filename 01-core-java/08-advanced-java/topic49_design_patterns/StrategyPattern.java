package topic49_design_patterns;

import java.util.List;

/*
 * Pattern  : Strategy - swap an algorithm without changing the code that uses it
 * Use for  : pricing rules, sorting orders, payment methods, validation rules.
 * Key idea : the varying behaviour is an interface; the class that uses it receives one.
 *            With a functional interface, a strategy is just a lambda.
 * Spring   : inject a different implementation of an interface - that's Strategy + DI (topic 14).
 * Run      : java -cp out topic49_design_patterns.StrategyPattern
 */
public class StrategyPattern {

    @FunctionalInterface
    interface PricingStrategy {
        double priceFor(double basePrice);
    }

    static class Checkout {
        private final PricingStrategy pricing;

        Checkout(PricingStrategy pricing) {           // the strategy is injected
            this.pricing = pricing;
        }

        double total(List<Double> items) {
            double sum = 0;
            for (double item : items) {
                sum += pricing.priceFor(item);         // Checkout never knows WHICH rule it uses
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        List<Double> cart = List.of(100.0, 250.0);

        PricingStrategy regular = price -> price;
        PricingStrategy festiveSale = price -> price * 0.8;
        PricingStrategy member = price -> Math.max(0, price - 30);

        System.out.println("regular: " + new Checkout(regular).total(cart));
        System.out.println("festive: " + new Checkout(festiveSale).total(cart));
        System.out.println("member:  " + new Checkout(member).total(cart));
    }
}
