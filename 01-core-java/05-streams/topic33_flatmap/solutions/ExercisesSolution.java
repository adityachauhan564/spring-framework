package topic33_flatmap.solutions;

import java.util.Arrays;
import java.util.List;

// Solutions for topic33_flatmap/Exercises.java
public class ExercisesSolution {

    static int total(List<List<Integer>> groups) {
        return groups.stream()
                .flatMap(List::stream)            // each inner list becomes part of ONE stream
                .mapToInt(Integer::intValue)
                .sum();
    }

    static List<String> distinctWords(List<String> sentences) {
        return sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.toLowerCase().split(" ")))
                .distinct()
                .sorted()
                .toList();
    }

    static List<String> combinations(List<String> sizes, List<String> colours) {
        return sizes.stream()
                .flatMap(size -> colours.stream().map(colour -> size + "-" + colour))   // a stream per size
                .toList();
    }

    public static void main(String[] args) {
        check(total(List.of(List.of(1, 2), List.of(3), List.of())) == 6, "exercise 1");
        check(distinctWords(List.of("The cat", "the dog")).equals(List.of("cat", "dog", "the")), "exercise 2");
        check(combinations(List.of("S", "M"), List.of("Red", "Blue"))
                .equals(List.of("S-Red", "S-Blue", "M-Red", "M-Blue")), "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
