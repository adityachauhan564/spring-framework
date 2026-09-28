package topic25_sets;

import java.util.List;
import java.util.Set;

/*
 * Exercises for topic 25. Replace each "TODO" line with your code, then run:
 *   java -cp out topic25_sets.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. true if any value appears twice. One pass, using a Set (hint: add() returns false for a duplicate).
    static boolean hasDuplicate(int[] values) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The words used in BOTH sentences, sorted: "the cat sat" and "the dog sat" -> [sat, the].
    //    Words are separated by single spaces; compare in lower case.
    static Set<String> commonWords(String first, String second) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. The distinct items in the order they first appear: [b, a, b, c] -> [b, a, c].
    //    Pick the Set implementation that keeps insertion order.
    static Set<String> distinctInOrder(List<String> items) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        check(hasDuplicate(new int[] {1, 5, 3, 5}) && !hasDuplicate(new int[] {1, 2, 3}), "exercise 1");
        check(commonWords("The cat sat", "the dog sat").toString().equals("[sat, the]"), "exercise 2");
        check(distinctInOrder(List.of("b", "a", "b", "c")).toString().equals("[b, a, c]"), "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
