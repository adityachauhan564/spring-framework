package topic49_design_patterns;

import java.util.ArrayList;
import java.util.List;

/*
 * Exercises for topic 49. Complete the code below, then run:
 *   java -cp out topic49_design_patterns.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    interface Payment {
        String pay(double amount);
    }

    // 1. STRATEGY + FACTORY: return the right Payment for "upi", "card" or "wallet" (as lambdas),
    //    answering "UPI paid <amount>", "Card paid <amount>", "Wallet paid <amount>".
    //    Throw IllegalArgumentException for anything else.
    static Payment paymentFor(String type) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    public static void main(String[] args) {
        check(paymentFor("upi").pay(100).equals("UPI paid 100.0"), "exercise 1 upi");
        check(paymentFor("wallet").pay(5).equals("Wallet paid 5.0"), "exercise 1 wallet");
        try {
            paymentFor("cash");
            check(false, "exercise 1 must reject an unknown type");
        } catch (IllegalArgumentException expected) {
            // rejected
        }

        // 2. BUILDER: size is required; cheese defaults to true; toppings are optional
        Pizza pizza = Pizza.size("large").topping("olive").topping("onion").build();
        check(pizza.toString().equals("large pizza, cheese, toppings [olive, onion]"), "exercise 2");
        check(Pizza.size("small").noCheese().build().toString().equals("small pizza, no cheese, toppings []"),
                "exercise 2 defaults");

        // 3. OBSERVER: every subscriber hears each new price
        PriceTicker ticker = new PriceTicker();
        List<String> heard = new ArrayList<>();
        ticker.subscribe(price -> heard.add("A:" + price));
        ticker.subscribe(price -> heard.add("B:" + price));
        ticker.publish(101);
        check(heard.equals(List.of("A:101", "B:101")), "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 2. Complete the builder: size(...) starts it, topping(...) adds, noCheese() turns cheese off, build() creates
final class Pizza {
    private final String size;
    private final boolean cheese;
    private final List<String> toppings;

    private Pizza(String size, boolean cheese, List<String> toppings) {
        this.size = size;
        this.cheese = cheese;
        this.toppings = List.copyOf(toppings);
    }

    static Builder size(String size) {
        return new Builder(size);
    }

    @Override
    public String toString() {
        return size + " pizza, " + (cheese ? "cheese" : "no cheese") + ", toppings " + toppings;
    }

    static final class Builder {
        private final String size;

        private Builder(String size) {
            this.size = size;
        }

        Builder topping(String topping) {
            throw new UnsupportedOperationException("TODO exercise 2");
        }

        Builder noCheese() {
            throw new UnsupportedOperationException("TODO exercise 2");
        }

        Pizza build() {
            throw new UnsupportedOperationException("TODO exercise 2");
        }
    }
}

// 3. Keep the listeners and notify each of them in publish()
class PriceTicker {
    interface Listener {
        void priceChanged(int price);
    }

    void subscribe(Listener listener) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    void publish(int price) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }
}
