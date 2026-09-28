package topic32_collectors;

import java.util.List;
import java.util.Map;

/*
 * Exercises for topic 32. Write each answer with collect(Collectors...), then run:
 *   java -cp out topic32_collectors.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. Group names by their first letter, keys sorted: [Asha, Ravi, Arun] -> {A=[Asha, Arun], R=[Ravi]}
    static Map<Character, List<String>> byFirstLetter(List<String> names) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Split marks into passed (>= 40) and failed: {false=[12, 35], true=[40, 88]}
    static Map<Boolean, List<Integer>> passFail(List<Integer> marks) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. One line of upper-case words separated by " | ": [a, b] -> "A | B"
    static String joinUpper(List<String> words) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Word -> how many times it appears, keys sorted: [to, be, to] -> {be=1, to=2}
    static Map<String, Long> wordCounts(List<String> words) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        check(byFirstLetter(List.of("Asha", "Ravi", "Arun")).toString().equals("{A=[Asha, Arun], R=[Ravi]}"), "exercise 1");
        check(passFail(List.of(12, 40, 35, 88)).toString().equals("{false=[12, 35], true=[40, 88]}"), "exercise 2");
        check(joinUpper(List.of("spring", "boot")).equals("SPRING | BOOT"), "exercise 3");
        check(wordCounts(List.of("to", "be", "or", "not", "to", "be")).toString().equals("{be=2, not=1, or=1, to=2}"),
                "exercise 4");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
