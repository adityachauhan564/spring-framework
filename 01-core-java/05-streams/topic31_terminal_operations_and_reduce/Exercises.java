package topic31_terminal_operations_and_reduce;

import java.util.List;

/*
 * Exercises for topic 31.
 * How to use:
 *   - Write each answer as ONE stream pipeline. Delete the "TODO" line.
 *   - Then run:  java -cp out topic31_terminal_operations_and_reduce.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. The longest name, using reduce. If two have the same length, keep the earlier one.
    //    You can assume the list is not empty.
    static String longest(List<String> names) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. All the numbers multiplied together, using reduce. Think: what should the starting value be?
    static int product(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Return true if every word is written in small letters.
    static boolean allLowerCase(List<String> words) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. The first number that is bigger than the limit, or -1 if there is none.
    static int firstAbove(List<Integer> numbers, int limit) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        check(longest(List.of("API", "Docker", "Spring")).equals("Docker"), "exercise 1");
        check(product(List.of(2, 3, 4)) == 24 && product(List.of()) == 1, "exercise 2");
        check(allLowerCase(List.of("java", "streams")) && !allLowerCase(List.of("java", "API")), "exercise 3");
        check(firstAbove(List.of(3, 12, 9, 20), 10) == 12 && firstAbove(List.of(1, 2), 10) == -1, "exercise 4");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
