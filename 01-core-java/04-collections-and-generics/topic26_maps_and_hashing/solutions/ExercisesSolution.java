package topic26_maps_and_hashing.solutions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Answers for topic26_maps_and_hashing/Exercises.java
public class ExercisesSolution {

    static Map<Character, Integer> charFrequency(String text) {
        Map<Character, Integer> counts = new TreeMap<>();            // TreeMap keeps the keys sorted
        for (char c : text.toCharArray()) {
            counts.merge(c, 1, Integer::sum);                        // new character -> 1, seen before -> old count + 1
        }
        return counts;
    }

    static char firstUnique(String text) {
        Map<Character, Integer> counts = new LinkedHashMap<>();      // remembers the order characters first appeared
        for (char c : text.toCharArray()) {
            counts.merge(c, 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {   // walk in first-seen order
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return '_';
    }

    static Map<Integer, List<String>> byLength(List<String> words) {
        Map<Integer, List<String>> groups = new TreeMap<>();
        for (String word : words) {
            // "give me the list for this length - and if there isn't one yet, create an empty one first"
            groups.computeIfAbsent(word.length(), length -> new ArrayList<>()).add(word);
        }
        return groups;
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
