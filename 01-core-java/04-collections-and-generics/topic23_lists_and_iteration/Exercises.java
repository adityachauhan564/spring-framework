package topic23_lists_and_iteration;

import java.util.ArrayList;
import java.util.List;

/*
 * Exercises for topic 23.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic23_lists_and_iteration.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Return a NEW list without duplicates. Keep the first time each item appears:
    //    [b, a, b, c, a] -> [b, a, c]
    static List<String> withoutDuplicates(List<String> items) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Remove every negative number FROM the given list itself (change it, return nothing).
    //    Don't call remove() inside a for-each loop.
    static void removeNegatives(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Swap the first and the last item. A list with fewer than 2 items stays as it is.
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
