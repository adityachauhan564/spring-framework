package topic26_maps_and_hashing.solutions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Solutions for topic26_maps_and_hashing/Exercises.java
public class ExercisesSolution {

    static Map<Character, Integer> charFrequency(String text) {
        Map<Character, Integer> counts = new TreeMap<>();            // TreeMap: keys sorted
        for (char c : text.toCharArray()) {
            counts.merge(c, 1, Integer::sum);                        // 1 for a new key, otherwise old + 1
        }
        return counts;
    }

    static char firstUnique(String text) {
        Map<Character, Integer> counts = new LinkedHashMap<>();      // keeps first-seen order
        for (char c : text.toCharArray()) {
            counts.merge(c, 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return '_';
    }

    static Map<Integer, List<String>> byLength(List<String> words) {
        Map<Integer, List<String>> groups = new TreeMap<>();
        for (String word : words) {
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
