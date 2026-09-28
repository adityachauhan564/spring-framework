package topic42_parallel_streams;

import java.util.List;

/*
 * Exercises for topic 42. Replace each "TODO" line with your code, then run:
 *   java -cp out topic42_parallel_streams.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    static boolean isPrime(int n) {
        for (int d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return n >= 2;
    }

    // 1. Count the primes up to n with a PARALLEL stream (IntStream.rangeClosed(...).parallel()).
    static long countPrimes(int n) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The squares of 1..n, IN ORDER, computed in parallel. The version in the comment is buggy:
    //      List<Integer> result = new ArrayList<>();
    //      IntStream.rangeClosed(1, n).parallel().forEach(i -> result.add(i * i));
    //    Why? Write a correct one that still uses parallel().
    static List<Integer> squares(int n) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    public static void main(String[] args) {
        check(countPrimes(100_000) == 9592, "exercise 1");
        List<Integer> result = squares(10_000);
        check(result.size() == 10_000 && result.get(0) == 1 && result.get(9_999) == 100_000_000, "exercise 2");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
