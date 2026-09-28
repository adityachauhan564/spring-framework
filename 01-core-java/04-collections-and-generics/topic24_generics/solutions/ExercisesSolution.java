package topic24_generics.solutions;

import java.util.Arrays;
import java.util.List;

// Solutions for topic24_generics/Exercises.java
public class ExercisesSolution {

    static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];                      // T is whatever the caller's array holds
        array[i] = array[j];
        array[j] = temp;
    }

    static <T extends Comparable<T>> int countGreaterThan(List<T> items, T value) {
        int count = 0;
        for (T item : items) {
            if (item.compareTo(value) > 0) {    // allowed because T extends Comparable<T>
                count++;
            }
        }
        return count;
    }

    static double maxValue(List<? extends Number> numbers) {
        double max = Double.NEGATIVE_INFINITY;
        for (Number n : numbers) {              // reading as Number is safe for any subtype
            max = Math.max(max, n.doubleValue());
        }
        return max;
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
        return new Pair<>(second, first);       // the types swap places too
    }
}
