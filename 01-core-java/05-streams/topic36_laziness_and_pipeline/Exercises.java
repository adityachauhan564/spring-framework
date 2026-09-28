package topic36_laziness_and_pipeline;

import java.util.ArrayList;
import java.util.List;

/*
 * Exercises for topic 36. Replace each "TODO" line with your code, then run:
 *   java -cp out topic36_laziness_and_pipeline.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. PREDICT, then check: which elements does filter() look at before findFirst() stops?
    //    Replace the list below with your prediction for the pipeline in main.
    static final List<String> PREDICTED_TRACE = List.of("TODO");

    // 2. The first n prime numbers, from an INFINITE stream (Stream.iterate from 2, then filter, then limit).
    static List<Integer> firstPrimes(int n) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. The first square above the limit: 50 -> 64. Use an infinite stream and findFirst.
    static int firstSquareAbove(int limit) {
        throw new UnsupportedOperationException("TODO exercise 3");
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
                .peek(seen::add)                       // records what reached the filter (debugging only!)
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
