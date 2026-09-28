package topic26_maps_and_hashing;

import java.util.List;
import java.util.Map;

/*
 * Exercises for topic 26. Replace each "TODO" line with your code, then run:
 *   java -cp out topic26_maps_and_hashing.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. How often each character appears, sorted by character: "banana" -> {a=3, b=1, n=2}.
    static Map<Character, Integer> charFrequency(String text) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The first character that appears only once: "swiss" -> 'w'. Return '_' if there is none.
    //    Which Map keeps the characters in the order they were first seen?
    static char firstUnique(String text) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Group words by their length: [hi, java, is, fun] -> {2=[hi, is], 3=[fun], 4=[java]} (sorted by length).
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
