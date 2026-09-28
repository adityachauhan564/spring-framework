package topic34_creating_and_primitive_streams;

import java.util.List;

/*
 * Exercises for topic 34. Write each answer as one stream pipeline, then run:
 *   java -cp out topic34_creating_and_primitive_streams.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. The first n powers of 2: 5 -> [1, 2, 4, 8, 16]. (Stream.iterate + limit)
    static List<Integer> powersOfTwo(int n) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The sum of the multiples of 3 or 5 from 1 to n inclusive: 10 -> 3+5+6+9+10 = 33. (IntStream)
    static int sumOfMultiples(int n) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. The average length of the words, or 0 for an empty list. (mapToInt + average)
    static double averageLength(List<String> words) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. The largest value in an int array, or -1 for an empty array. (Arrays.stream)
    static int maxOf(int[] values) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        check(powersOfTwo(5).equals(List.of(1, 2, 4, 8, 16)), "exercise 1");
        check(sumOfMultiples(10) == 33, "exercise 2");
        check(averageLength(List.of("ab", "abcd")) == 3 && averageLength(List.of()) == 0, "exercise 3");
        check(maxOf(new int[] {4, 9, 2}) == 9 && maxOf(new int[] {}) == -1, "exercise 4");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
