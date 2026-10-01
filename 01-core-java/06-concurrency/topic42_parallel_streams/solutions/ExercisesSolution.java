package topic42_parallel_streams.solutions;

import java.util.List;
import java.util.stream.IntStream;

// Answers for topic42_parallel_streams/Exercises.java
public class ExercisesSolution {

    // helper: true if n is a prime number
    static boolean isPrime(int n) {
        for (int d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return n >= 2;
    }

    // Lots of separate, CPU-heavy checks - each number can be tested on its own. A good fit for parallel()
    static long countPrimes(int n) {
        return IntStream.rangeClosed(1, n).parallel().filter(ExercisesSolution::isPrime).count();
    }

    // Why the given version is buggy: many threads add to ONE shared ArrayList at the same time.
    // Items get lost (or it crashes), and the order comes out random.
    // The fix: let the stream build the list. toList() keeps the original order, even when parallel.
    static List<Integer> squares(int n) {
        return IntStream.rangeClosed(1, n).parallel().map(i -> i * i).boxed().toList();
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
