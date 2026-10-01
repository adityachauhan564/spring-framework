package topic36_laziness_and_pipeline;

import java.util.ArrayList;
import java.util.List;

/*
 * Exercises for topic 36.
 * How to use:
 *   - Replace each "TODO" with your own code.
 *   - Then run:  java -cp out topic36_laziness_and_pipeline.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. GUESS first, then check: in the pipeline inside main, which items does filter() look at
    //    before findFirst() stops? Replace the list below with your guess.
    static final List<String> PREDICTED_TRACE = List.of("TODO");

    // 2. The first n prime numbers, taken from a NEVER-ENDING stream:
    //    Stream.iterate from 2, then filter, then limit.
    static List<Integer> firstPrimes(int n) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. The first square number bigger than the limit: 50 -> 64. Use an endless stream and findFirst.
    static int firstSquareAbove(int limit) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // helper: true if n is a prime number (already done for you)
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
                .peek(seen::add)                       // writes down every item that reaches the filter (for debugging only!)
                .filter(course -> course.length() == 3)
                .findFirst();
        check(seen.equals(PREDICTED_TRACE), "exercise 1: the real trace was " + seen + ", not");

        check(firstPrimes(5).equals(List.of(2, 3, 5, 7, 11)), "exercise 2");
        check(firstSquareAbove(50) == 64 && firstSquareAbove(0) == 1, "exercise 3");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
