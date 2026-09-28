package topic33_flatmap;

import java.util.List;

/*
 * Exercises for topic 33. Write each answer as one stream pipeline using flatMap, then run:
 *   java -cp out topic33_flatmap.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. The sum of all numbers in a list of lists: [[1, 2], [3], []] -> 6
    static int total(List<List<Integer>> groups) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Every distinct word of all sentences, sorted, in lower case:
    //    ["The cat", "the dog"] -> [cat, dog, the]
    static List<String> distinctWords(List<String> sentences) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Every "size-colour" combination, sizes first: ([S, M], [Red, Blue]) -> [S-Red, S-Blue, M-Red, M-Blue]
    static List<String> combinations(List<String> sizes, List<String> colours) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        check(total(List.of(List.of(1, 2), List.of(3), List.of())) == 6, "exercise 1");
        check(distinctWords(List.of("The cat", "the dog")).equals(List.of("cat", "dog", "the")), "exercise 2");
        check(combinations(List.of("S", "M"), List.of("Red", "Blue"))
                .equals(List.of("S-Red", "S-Blue", "M-Red", "M-Blue")), "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
