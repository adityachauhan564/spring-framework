package topic37_higher_order_functions;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Topic    : Higher-order functions - functions that take other functions, or give back functions
 * Key idea : filter() and map() already TAKE a function as input.
 *            You can also write a method that RETURNS a new function, built from its parameters.
 *            This is called a "function factory".
 *            Like a juice shop: you tell the shopkeeper "mango, less sugar" and he makes
 *            exactly that juice for you. discount(10) makes a "10% off" function for you.
 * Run      : java -cp out topic37_higher_order_functions.HigherOrderFunctions
 * Try this : Write timesTable(n) that returns a Function<Integer, Integer> that multiplies by n.
 */
public class HigherOrderFunctions {

    // returns a function: the test is built from the length you pass in
    static Predicate<String> longerThan(int length) {
        return text -> text.length() > length;
    }

    // returns a "percent off" function, like a coupon code at checkout
    static Function<Double, Double> discount(double percent) {
        return price -> price * (1 - percent / 100);
    }

    // takes a function and returns a NEW, improved function that also prints what it did
    static <T, R> Function<T, R> logged(String name, Function<T, R> function) {
        return input -> {
            R output = function.apply(input);          // do the original work
            System.out.println("  " + name + "(" + input + ") = " + output);   // the extra bit: print it
            return output;
        };
    }

    // currying: turn a two-input function into a chain of one-input functions.
    // adder().apply(5) gives a function that "adds 5 to whatever you give it"
    static Function<Integer, Function<Integer, Integer>> adder() {
        return a -> b -> a + b;
    }

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "API", "Microservices", "AWS", "Docker");

        System.out.println("longer than 3: " + courses.stream().filter(longerThan(3)).toList());
        System.out.println("longer than 6: " + courses.stream().filter(longerThan(6)).toList());

        Function<Double, Double> tenPercentOff = discount(10);
        Function<Double, Double> festiveSale = discount(10).andThen(discount(5));   // 10% off, and then another 5% off on top
        System.out.println("1000 with 10% off: " + tenPercentOff.apply(1000.0));
        System.out.println("1000 with 10% then 5% off: " + festiveSale.apply(1000.0));

        System.out.println("logged square:");
        List<Integer> squares = List.of(2, 3, 4).stream().map(logged("square", (Integer n) -> n * n)).toList();
        System.out.println("  -> " + squares);

        Function<Integer, Integer> addFive = adder().apply(5);
        System.out.println("adder().apply(5).apply(10) = " + addFive.apply(10));

        // putting work off until later: a Supplier does its work only when get() is called
        Supplier<String> expensive = () -> {
            System.out.println("  (expensive work running now)");
            return "report";
        };
        System.out.println("supplier created, nothing ran yet");
        System.out.println("got: " + expensive.get());
    }
}
