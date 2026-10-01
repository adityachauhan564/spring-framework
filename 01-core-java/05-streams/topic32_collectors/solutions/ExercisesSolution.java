package topic32_collectors.solutions;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

// Answers for topic32_collectors/Exercises.java
public class ExercisesSolution {

    static Map<Character, List<String>> byFirstLetter(List<String> names) {
        return names.stream().collect(Collectors.groupingBy(
                name -> name.charAt(0), TreeMap::new, Collectors.toList()));   // TreeMap::new makes the keys sorted
    }

    static Map<Boolean, List<Integer>> passFail(List<Integer> marks) {
        return marks.stream().collect(Collectors.partitioningBy(mark -> mark >= 40));   // always gives both keys, true and false
    }

    static String joinUpper(List<String> words) {
        return words.stream().map(String::toUpperCase).collect(Collectors.joining(" | "));
    }

    static Map<String, Long> wordCounts(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(
                word -> word, TreeMap::new, Collectors.counting()));      // bucket = the word itself, then count each bucket
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
