package topic26_maps_and_hashing;

import java.util.List;
import java.util.Map;

/*
 * Exercises for topic 26.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic26_maps_and_hashing.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Count how many times each character appears, sorted by character:
    //    "banana" -> {a=3, b=1, n=2}
    static Map<Character, Integer> charFrequency(String text) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Return the first character that appears only once: "swiss" -> 'w'. Return '_' if there is none.
    //    Think: which Map remembers the order in which characters were first seen?
    static char firstUnique(String text) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Group the words by their length, sorted by length:
    //    [hi, java, is, fun] -> {2=[hi, is], 3=[fun], 4=[java]}
    //    Hint: computeIfAbsent(key, k -> new ArrayList<>()).add(word)
    static Map<Integer, List<String>> byLength(List<String> words) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        check(charFrequency("banana").toString().equals("{a=3, b=1, n=2}"), "exercise 1");
        check(firstUnique("swiss") == 'w' && firstUnique("aabb") == '_', "exercise 2");
        check(byLength(List.of("hi", "java", "is", "fun")).toString().equals("{2=[hi, is], 3=[fun], 4=[java]}"),
                "exercise 3");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
