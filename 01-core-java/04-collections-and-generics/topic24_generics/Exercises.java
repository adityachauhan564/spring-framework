package topic24_generics;

import java.util.Arrays;
import java.util.List;

/*
 * Exercises for topic 24.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic24_generics.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Swap two positions in an array of ANY type.
    static <T> void swap(T[] array, int i, int j) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 3. How many items are bigger than the given value? T must be comparable with itself.
    static <T extends Comparable<T>> int countGreaterThan(List<T> items, T value) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. The biggest number in a list of ANY Number type (Integer, Double, ...), returned as a double.
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 2. A pair of two values, which can be of different types.
//    swap() must return a NEW Pair with the two values the other way round.
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
