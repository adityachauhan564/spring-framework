package topic42_parallel_streams.solutions;

import java.util.List;
import java.util.stream.IntStream;

// Solutions for topic42_parallel_streams/Exercises.java
public class ExercisesSolution {

    static boolean isPrime(int n) {
        for (int d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return n >= 2;
    }

    // CPU-heavy, independent checks on many numbers: a good fit for parallel()
    static long countPrimes(int n) {
        return IntStream.rangeClosed(1, n).parallel().filter(ExercisesSolution::isPrime).count();
    }

    // The buggy version adds to a shared ArrayList from many threads: elements get lost, or it throws,
    // and the order is random. Let the stream build the list: toList() keeps the encounter order.
    static List<Integer> squares(int n) {
        return IntStream.rangeClosed(1, n).parallel().map(i -> i * i).boxed().toList();
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
