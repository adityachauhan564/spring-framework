package topic42_parallel_streams;

import java.util.List;

/*
 * Exercises for topic 42.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic42_parallel_streams.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // helper: true if n is a prime number (already done for you)
    static boolean isPrime(int n) {
        for (int d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return n >= 2;
    }

    // 1. Count the prime numbers up to n, using a PARALLEL stream (IntStream.rangeClosed(...).parallel()).
    static long countPrimes(int n) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The squares of 1..n, IN ORDER, worked out in parallel. The version below has a bug:
    //      List<Integer> result = new ArrayList<>();
    //      IntStream.rangeClosed(1, n).parallel().forEach(i -> result.add(i * i));
    //    Can you see why? Write a correct version that still uses parallel().
    static List<Integer> squares(int n) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    public static void main(String[] args) {
        check(countPrimes(100_000) == 9592, "exercise 1");
        List<Integer> result = squares(10_000);
        check(result.size() == 10_000 && result.get(0) == 1 && result.get(9_999) == 100_000_000, "exercise 2");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
