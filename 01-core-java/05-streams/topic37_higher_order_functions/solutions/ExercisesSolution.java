package topic37_higher_order_functions.solutions;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

// Answers for topic37_higher_order_functions/Exercises.java
public class ExercisesSolution {

    static Function<Integer, Integer> timesTable(int n) {
        return x -> x * n;                              // the lambda remembers n, so each call makes a different function
    }

    static Predicate<Integer> between(int min, int max) {
        return x -> x >= min && x <= max;
    }

    static Function<Integer, Integer> twice(Function<Integer, Integer> f) {
        return f.andThen(f);                            // run f, then run f again. Same as: x -> f.apply(f.apply(x))
    }

    static Function<Integer, Integer> pipeline(List<Function<Integer, Integer>> steps) {
        // start with identity ("change nothing"), then join each step on with andThen
        return steps.stream().reduce(Function.identity(), Function::andThen);
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
