package topic13_higher_order_functions;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Topic    : Higher-order functions - functions that take or return functions
 * Key idea : filter/map already TAKE functions. You can also write methods that RETURN a
 *            function, to build behaviour from parameters (a "function factory").
 * Run      : java -cp out topic13_higher_order_functions.HigherOrderFunctions
 * Try this : write timesTable(n) that returns a Function<Integer, Integer> multiplying by n.
 */
public class HigherOrderFunctions {

    // returns a function: the predicate is built from the parameter
    static Predicate<String> longerThan(int length) {
        return text -> text.length() > length;
    }

    static Function<Double, Double> discount(double percent) {
        return price -> price * (1 - percent / 100);
    }

    // takes a function and returns a new, improved function
    static <T, R> Function<T, R> logged(String name, Function<T, R> function) {
        return input -> {
            R output = function.apply(input);
            System.out.println("  " + name + "(" + input + ") = " + output);
            return output;
        };
    }

    // currying: a two-argument function turned into a chain of one-argument functions
    static Function<Integer, Function<Integer, Integer>> adder() {
        return a -> b -> a + b;
    }

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "API", "Microservices", "AWS", "Docker");

        System.out.println("longer than 3: " + courses.stream().filter(longerThan(3)).toList());
        System.out.println("longer than 6: " + courses.stream().filter(longerThan(6)).toList());

        Function<Double, Double> tenPercentOff = discount(10);
        Function<Double, Double> festiveSale = discount(10).andThen(discount(5));   // chained discounts
        System.out.println("1000 with 10% off: " + tenPercentOff.apply(1000.0));
        System.out.println("1000 with 10% then 5% off: " + festiveSale.apply(1000.0));

        System.out.println("logged square:");
        List<Integer> squares = List.of(2, 3, 4).stream().map(logged("square", (Integer n) -> n * n)).toList();
        System.out.println("  -> " + squares);

        Function<Integer, Integer> addFive = adder().apply(5);
        System.out.println("adder().apply(5).apply(10) = " + addFive.apply(10));

        // deferred work: a Supplier runs only when get() is called
        Supplier<String> expensive = () -> {
            System.out.println("  (expensive work running now)");
            return "report";
        };
        System.out.println("supplier created, nothing ran yet");
        System.out.println("got: " + expensive.get());
    }
}
