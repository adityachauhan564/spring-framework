package topic33_flatmap;

import java.util.List;

/*
 * Exercises for topic 33.
 * How to use:
 *   - Write each answer as ONE stream pipeline that uses flatMap. Delete the "TODO" line.
 *   - Then run:  java -cp out topic33_flatmap.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Add up every number in a list of lists: [[1, 2], [3], []] -> 6
    static int total(List<List<Integer>> groups) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Every word from all the sentences - each word once, sorted, in small letters:
    //    ["The cat", "the dog"] -> [cat, dog, the]
    static List<String> distinctWords(List<String> sentences) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Every "size-colour" pair, going size by size:
    //    ([S, M], [Red, Blue]) -> [S-Red, S-Blue, M-Red, M-Blue]
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
