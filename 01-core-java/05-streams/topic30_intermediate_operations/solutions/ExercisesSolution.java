package topic30_intermediate_operations.solutions;

import java.util.Comparator;
import java.util.List;

// Answers for topic30_intermediate_operations/Exercises.java
public class ExercisesSolution {

    static List<Integer> oddSquaresDescending(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 != 0)                // only odd
                .distinct()                             // no repeats
                .map(n -> n * n)                        // square each
                .sorted(Comparator.reverseOrder())      // biggest first
                .toList();
    }

    static List<String> twoLongest(List<String> courses) {
        return courses.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())   // sorted() is "stable": equal items keep their old order
                .limit(2)
                .toList();
    }

    static List<String> header(List<String> lines) {
        return lines.stream()
                .takeWhile(line -> !line.isBlank())     // stops completely at the FIRST blank line. filter() would keep going
                .map(String::trim)
                .toList();
    }

    static List<String> page2(List<String> items) {
        int pageSize = 3;
        return items.stream().skip(pageSize).limit(pageSize).toList();   // skip page 1, then take one page
    }

    public static void main(String[] args) {
        check(oddSquaresDescending(List.of(3, 1, 3, 2)).equals(List.of(9, 1)), "exercise 1");
        check(twoLongest(List.of("API", "Microservices", "Spring", "Docker")).equals(List.of("Microservices", "Spring")),
                "exercise 2");
        check(header(List.of("a ", " b", "", "c")).equals(List.of("a", "b")), "exercise 3");
        check(page2(List.of("1", "2", "3", "4", "5", "6", "7")).equals(List.of("4", "5", "6")), "exercise 4");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
