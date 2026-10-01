package topic37_higher_order_functions;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/*
 * Exercises for topic 37.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic37_higher_order_functions.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Return a function that multiplies by n, like a times table: timesTable(3).apply(4) == 12
    static Function<Integer, Integer> timesTable(int n) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Return a test that is true for values from min to max (both included).
    static Predicate<Integer> between(int min, int max) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Take a function, and return a new one that runs it TWICE: twice(x -> x + 3).apply(1) == 7
    static Function<Integer, Integer> twice(Function<Integer, Integer> f) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Join any number of functions into one, running them from left to right:
    //    pipeline(List.of(add1, double)).apply(5) == 12.
    //    An empty list must give back a function that changes nothing.
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
