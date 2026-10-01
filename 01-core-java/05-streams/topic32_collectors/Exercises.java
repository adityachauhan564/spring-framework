package topic32_collectors;

import java.util.List;
import java.util.Map;

/*
 * Exercises for topic 32.
 * How to use:
 *   - Write each answer using collect(Collectors...). Delete the "TODO" line.
 *   - Then run:  java -cp out topic32_collectors.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Group the names by their first letter, with the letters sorted:
    //    [Asha, Ravi, Arun] -> {A=[Asha, Arun], R=[Ravi]}
    static Map<Character, List<String>> byFirstLetter(List<String> names) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Split the marks into passed (40 or more) and failed: {false=[12, 35], true=[40, 88]}
    static Map<Boolean, List<Integer>> passFail(List<Integer> marks) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. One line of CAPITAL words with " | " between them: [a, b] -> "A | B"
    static String joinUpper(List<String> words) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Each word -> how many times it appears, with the words sorted: [to, be, to] -> {be=1, to=2}
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
