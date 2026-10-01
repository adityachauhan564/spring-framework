package topic25_sets;

import java.util.List;
import java.util.Set;

/*
 * Exercises for topic 25.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic25_sets.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Return true if any value appears twice. Go through the array only once, using a Set.
    //    Hint: add() returns false when the value is already in the set.
    static boolean hasDuplicate(int[] values) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The words that appear in BOTH sentences, in sorted order:
    //    "the cat sat" and "the dog sat" -> [sat, the].
    //    Words have one space between them. Compare them in lower case.
    static Set<String> commonWords(String first, String second) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Each item only once, in the order it first appeared: [b, a, b, c] -> [b, a, c].
    //    Pick the kind of Set that remembers the order you added things in.
    static Set<String> distinctInOrder(List<String> items) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        check(hasDuplicate(new int[] {1, 5, 3, 5}) && !hasDuplicate(new int[] {1, 2, 3}), "exercise 1");
        check(commonWords("The cat sat", "the dog sat").toString().equals("[sat, the]"), "exercise 2");
        check(distinctInOrder(List.of("b", "a", "b", "c")).toString().equals("[b, a, c]"), "exercise 3");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
