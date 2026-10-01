package topic29_structured_vs_functional.solutions;

import java.util.List;

// Answers for topic29_structured_vs_functional/Exercises.java
public class ExercisesSolution {

    static List<Integer> evens(List<Integer> numbers) {
        return numbers.stream().filter(n -> n % 2 == 0).toList();                 // keep the evens, collect into a list
    }

    static List<String> startingWith(List<String> courses, String prefix) {
        return courses.stream().filter(course -> course.startsWith(prefix)).toList();
    }

    static long countLong(List<String> courses) {
        return courses.stream().filter(course -> course.length() >= 6).count();  // count() replaces the count++ loop
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 6, 13, 19, 27, 31);
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "Docker");

        check(evens(numbers).equals(List.of(12, 6)), "exercise 1");
        check(startingWith(courses, "Spring").equals(List.of("Spring", "Spring Boot")), "exercise 2");
        check(countLong(courses) == 4, "exercise 3");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
