package topic49_design_patterns.solutions;

import java.util.ArrayList;
import java.util.List;

// Answers for topic49_design_patterns/Exercises.java
public class ExercisesSolution {

    interface Payment {
        String pay(double amount);
    }

    static Payment paymentFor(String type) {
        return switch (type) {                          // the factory: the only place that knows all the options
            case "upi" -> amount -> "UPI paid " + amount;         // each strategy is just a lambda
            case "card" -> amount -> "Card paid " + amount;
            case "wallet" -> amount -> "Wallet paid " + amount;
            default -> throw new IllegalArgumentException("unknown payment type: " + type);
        };
    }

    public static void main(String[] args) {
        check(paymentFor("upi").pay(100).equals("UPI paid 100.0"), "exercise 1 upi");
        check(paymentFor("wallet").pay(5).equals("Wallet paid 5.0"), "exercise 1 wallet");
        try {
            paymentFor("cash");
            check(false, "exercise 1 must reject an unknown type");
        } catch (IllegalArgumentException expected) {
            // refused - correct
        }

        Pizza pizza = Pizza.size("large").topping("olive").topping("onion").build();
        check(pizza.toString().equals("large pizza, cheese, toppings [olive, onion]"), "exercise 2");
        check(Pizza.size("small").noCheese().build().toString().equals("small pizza, no cheese, toppings []"),
                "exercise 2 defaults");

        PriceTicker ticker = new PriceTicker();
        List<String> heard = new ArrayList<>();
        ticker.subscribe(price -> heard.add("A:" + price));
        ticker.subscribe(price -> heard.add("B:" + price));
        ticker.publish(101);
        check(heard.equals(List.of("A:101", "B:101")), "exercise 3");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

final class Pizza {
    private final String size;
    private final boolean cheese;
    private final List<String> toppings;

    private Pizza(String size, boolean cheese, List<String> toppings) {
        this.size = size;
        this.cheese = cheese;
        this.toppings = List.copyOf(toppings);          // a locked copy: the builder can't change this pizza later
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
        private boolean cheese = true;                  // default: cheese is on
        private final List<String> toppings = new ArrayList<>();

        private Builder(String size) {
            this.size = size;
        }

        Builder topping(String topping) {
            toppings.add(topping);
            return this;                                // return 'this' so the calls can be chained
        }

        Builder noCheese() {
            cheese = false;
            return this;
        }

        Pizza build() {
            return new Pizza(size, cheese, toppings);
        }
    }
}

class PriceTicker {
    interface Listener {
        void priceChanged(int price);
    }

    private final List<Listener> listeners = new ArrayList<>();     // everyone who subscribed

    void subscribe(Listener listener) {
        listeners.add(listener);
    }

    void publish(int price) {
        for (Listener listener : listeners) {           // tell each subscriber, one by one
            listener.priceChanged(price);
        }
    }
}
