package topic23_lists_and_iteration;

import java.util.ArrayList;
import java.util.List;

/*
 * Exercises for topic 23. Replace each "TODO" line with your code, then run:
 *   java -cp out topic23_lists_and_iteration.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. A NEW list without duplicates, keeping the first occurrence of each: [b, a, b, c, a] -> [b, a, c]
    static List<String> withoutDuplicates(List<String> items) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Remove every negative number FROM the given list (change it; return nothing).
    //    Don't call remove() inside a for-each loop.
    static void removeNegatives(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Swap the first and the last element (lists with fewer than 2 elements stay as they are).
    static void swapEnds(List<String> items) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        check(withoutDuplicates(List.of("b", "a", "b", "c", "a")).equals(List.of("b", "a", "c")), "exercise 1");

        List<Integer> numbers = new ArrayList<>(List.of(3, -1, 4, -1, -5, 9));
        removeNegatives(numbers);
        check(numbers.equals(List.of(3, 4, 9)), "exercise 2");

        List<String> letters = new ArrayList<>(List.of("x", "y", "z"));
        swapEnds(letters);
        check(letters.equals(List.of("z", "y", "x")), "exercise 3");
        List<String> single = new ArrayList<>(List.of("only"));
        swapEnds(single);
        check(single.equals(List.of("only")), "exercise 3 with one element");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
