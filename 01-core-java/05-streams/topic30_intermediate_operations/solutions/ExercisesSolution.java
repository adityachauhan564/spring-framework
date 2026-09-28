package topic30_intermediate_operations.solutions;

import java.util.Comparator;
import java.util.List;

// Solutions for topic30_intermediate_operations/Exercises.java
public class ExercisesSolution {

    static List<Integer> oddSquaresDescending(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 != 0)
                .distinct()
                .map(n -> n * n)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    static List<String> twoLongest(List<String> courses) {
        return courses.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())   // sorted() is stable: ties keep their order
                .limit(2)
                .toList();
    }

    static List<String> header(List<String> lines) {
        return lines.stream()
                .takeWhile(line -> !line.isBlank())     // stops at the FIRST blank line, unlike filter
                .map(String::trim)
                .toList();
    }

    static List<String> page2(List<String> items) {
        int pageSize = 3;
        return items.stream().skip(pageSize).limit(pageSize).toList();   // skip page 1, take page 2
    }

    public static void main(String[] args) {
        check(oddSquaresDescending(List.of(3, 1, 3, 2)).equals(List.of(9, 1)), "exercise 1");
        check(twoLongest(List.of("API", "Microservices", "Spring", "Docker")).equals(List.of("Microservices", "Spring")),
                "exercise 2");
        check(header(List.of("a ", " b", "", "c")).equals(List.of("a", "b")), "exercise 3");
        check(page2(List.of("1", "2", "3", "4", "5", "6", "7")).equals(List.of("4", "5", "6")), "exercise 4");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
