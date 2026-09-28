package topic31_terminal_operations_and_reduce;

import java.util.List;

/*
 * Exercises for topic 31. Write each answer as one stream pipeline, then run:
 *   java -cp out topic31_terminal_operations_and_reduce.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. The longest name, using reduce (for equal lengths keep the earlier one). Assume a non-empty list.
    static String longest(List<String> names) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The product of all numbers, using reduce. What must the starting value be?
    static int product(List<Integer> numbers) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. true if every word is written in lower case.
    static boolean allLowerCase(List<String> words) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. The first number above the limit, or -1 if there is none.
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
