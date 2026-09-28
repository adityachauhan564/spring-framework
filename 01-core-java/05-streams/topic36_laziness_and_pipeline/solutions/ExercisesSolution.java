package topic36_laziness_and_pipeline.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Solutions for topic36_laziness_and_pipeline/Exercises.java
public class ExercisesSolution {

    // One element at a time: Spring fails the filter, API passes, findFirst stops - the rest are never read
    static final List<String> PREDICTED_TRACE = List.of("Spring", "API");

    static List<Integer> firstPrimes(int n) {
        return Stream.iterate(2, x -> x + 1)       // 2, 3, 4, ... forever
                .filter(ExercisesSolution::isPrime)
                .limit(n)                          // laziness: only as many numbers as needed are generated
                .toList();
    }

    static int firstSquareAbove(int limit) {
        return Stream.iterate(1, x -> x + 1)
                .map(x -> x * x)
                .filter(square -> square > limit)
                .findFirst()
                .orElseThrow();                    // an infinite stream always finds one here
    }

    static boolean isPrime(int n) {
        for (int d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return n >= 2;
    }

    public static void main(String[] args) {
        List<String> seen = new ArrayList<>();
        List.of("Spring", "API", "Microservices", "AWS", "Docker").stream()
                .peek(seen::add)
                .filter(course -> course.length() == 3)
                .findFirst();
        check(seen.equals(PREDICTED_TRACE), "exercise 1: the real trace was " + seen + ", not");

        check(firstPrimes(5).equals(List.of(2, 3, 5, 7, 11)), "exercise 2");
        check(firstSquareAbove(50) == 64 && firstSquareAbove(0) == 1, "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
