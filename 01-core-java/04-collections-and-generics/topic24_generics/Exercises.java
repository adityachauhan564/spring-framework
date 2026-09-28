package topic24_generics;

import java.util.Arrays;
import java.util.List;

/*
 * Exercises for topic 24. Replace each "TODO" line with your code, then run:
 *   java -cp out topic24_generics.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. Swap two positions of ANY array type.
    static <T> void swap(T[] array, int i, int j) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 3. How many elements are greater than the given value? T must be comparable to itself.
    static <T extends Comparable<T>> int countGreaterThan(List<T> items, T value) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. The largest number in a list of ANY Number type (Integer, Double, ...), as a double.
    static double maxValue(List<? extends Number> numbers) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        String[] words = {"a", "b", "c"};
        swap(words, 0, 2);
        check(Arrays.equals(words, new String[] {"c", "b", "a"}), "exercise 1");

        Pair<String, Integer> age = new Pair<>("age", 25);
        Pair<Integer, String> flipped = age.swap();
        check(flipped.first() == 25 && flipped.second().equals("age"), "exercise 2");

        check(countGreaterThan(List.of(3, 8, 1, 9), 3) == 2, "exercise 3 with Integers");
        check(countGreaterThan(List.of("pear", "apple", "zebra"), "mango") == 2, "exercise 3 with Strings");

        check(maxValue(List.of(1, 7, 3)) == 7 && maxValue(List.of(1.5, 0.5)) == 1.5, "exercise 4");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 2. A pair of two values of possibly different types; swap() returns a Pair with them the other way round.
class Pair<A, B> {
    private final A first;
    private final B second;

    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    A first() {
        return first;
    }

    B second() {
        return second;
    }

    Pair<B, A> swap() {
        throw new UnsupportedOperationException("TODO exercise 2");
    }
}
