package topic30_intermediate_operations;

import java.util.List;

/*
 * Exercises for topic 30. Write each answer as one stream pipeline, then run:
 *   java -cp out topic30_intermediate_operations.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. The squares of the DISTINCT ODD numbers, largest first: [3, 1, 3, 2] -> [9, 1]
    static List<Integer> oddSquaresDescending(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The 2 longest course names, longest first (for equal lengths, keep the original order).
    static List<String> twoLongest(List<String> courses) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Every line BEFORE the first blank one, trimmed: ["a ", " b", "", "c"] -> [a, b]. (takeWhile)
    static List<String> header(List<String> lines) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Page 2 of the items when a page holds 3 items: items 4 to 6. (skip + limit)
    static List<String> page2(List<String> items) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        check(oddSquaresDescending(List.of(3, 1, 3, 2)).equals(List.of(9, 1)), "exercise 1");
        check(twoLongest(List.of("API", "Microservices", "Spring", "Docker")).equals(List.of("Microservices", "Spring")),
                "exercise 2");
        check(header(List.of("a ", " b", "", "c")).equals(List.of("a", "b")), "exercise 3");
        check(page2(List.of("1", "2", "3", "4", "5", "6", "7")).equals(List.of("4", "5", "6")), "exercise 4");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
