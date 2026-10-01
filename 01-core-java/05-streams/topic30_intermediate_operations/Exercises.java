package topic30_intermediate_operations;

import java.util.List;

/*
 * Exercises for topic 30.
 * How to use:
 *   - Write each answer as ONE stream pipeline. Delete the "TODO" line.
 *   - Then run:  java -cp out topic30_intermediate_operations.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Squares of the odd numbers, without duplicates, biggest first: [3, 1, 3, 2] -> [9, 1]
    static List<Integer> oddSquaresDescending(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The 2 longest course names, longest first.
    //    If two have the same length, keep them in their original order.
    static List<String> twoLongest(List<String> courses) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Every line BEFORE the first blank line, with spaces trimmed: ["a ", " b", "", "c"] -> [a, b].
    //    (Use takeWhile.)
    static List<String> header(List<String> lines) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Page 2 of the items, when one page shows 3 items - so items 4 to 6.
    //    Like page 2 of Flipkart search results. (Use skip + limit.)
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
