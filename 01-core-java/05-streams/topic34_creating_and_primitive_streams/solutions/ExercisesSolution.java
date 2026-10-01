package topic34_creating_and_primitive_streams.solutions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Answers for topic34_creating_and_primitive_streams/Exercises.java
public class ExercisesSolution {

    static List<Integer> powersOfTwo(int n) {
        return Stream.iterate(1, x -> x * 2).limit(n).toList();    // an endless stream, cut down to n items
    }

    static int sumOfMultiples(int n) {
        return IntStream.rangeClosed(1, n)                          // rangeClosed: n itself is included
                .filter(x -> x % 3 == 0 || x % 5 == 0)
                .sum();
    }

    static double averageLength(List<String> words) {
        return words.stream().mapToInt(String::length).average().orElse(0);   // average() may have no value (empty list), so orElse(0)
    }

    static int maxOf(int[] values) {
        return Arrays.stream(values).max().orElse(-1);              // gives an IntStream: plain ints, no boxing
    }

    public static void main(String[] args) {
        check(powersOfTwo(5).equals(List.of(1, 2, 4, 8, 16)), "exercise 1");
        check(sumOfMultiples(10) == 33, "exercise 2");
        check(averageLength(List.of("ab", "abcd")) == 3 && averageLength(List.of()) == 0, "exercise 3");
        check(maxOf(new int[] {4, 9, 2}) == 9 && maxOf(new int[] {}) == -1, "exercise 4");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
