package topic37_higher_order_functions;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/*
 * Exercises for topic 37. Replace each "TODO" line with your code, then run:
 *   java -cp out topic37_higher_order_functions.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. A function that multiplies by n: timesTable(3).apply(4) == 12
    static Function<Integer, Integer> timesTable(int n) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. A predicate that is true for values from min to max inclusive.
    static Predicate<Integer> between(int min, int max) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Take a function and return one that applies it TWICE: twice(x -> x + 3).apply(1) == 7
    static Function<Integer, Integer> twice(Function<Integer, Integer> f) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Chain any number of functions into one, applied left to right.
    //    pipeline(List.of(add1, double)).apply(5) == 12. An empty list gives a function that changes nothing.
    static Function<Integer, Integer> pipeline(List<Function<Integer, Integer>> steps) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        check(timesTable(3).apply(4) == 12, "exercise 1");
        check(List.of(5, 12, 18, 25).stream().filter(between(10, 20)).toList().equals(List.of(12, 18)), "exercise 2");
        check(twice(x -> x + 3).apply(1) == 7 && twice(x -> x * x).apply(3) == 81, "exercise 3");

        Function<Integer, Integer> add1 = x -> x + 1;
        Function<Integer, Integer> doubleIt = x -> x * 2;
        check(pipeline(List.of(add1, doubleIt)).apply(5) == 12, "exercise 4");
        check(pipeline(List.of()).apply(5) == 5, "exercise 4 with no steps");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
