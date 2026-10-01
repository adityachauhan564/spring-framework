package topic29_structured_vs_functional;

import java.util.List;

/*
 * Topic    : filter() practice (from the in28minutes FP01 exercises)
 * Key idea : filter(condition) keeps ONLY the items for which the lambda says true.
 *            Like a security guard at a gate: "only people with a pass can go in".
 * Run      : java -cp out topic29_structured_vs_functional.CourseFilterExercises
 * Try this : Print the courses that contain a space, then the numbers divisible by 3.
 */
public class CourseFilterExercises {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 6, 13, 19, 27, 31);
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices",
                "System Design", "Docker", "Kubernetes");

        // Exercise 1: only the odd numbers
        System.out.println("Odd numbers:");
        numbers.stream()
                .filter(number -> number % 2 != 0)
                .forEach(System.out::println);

        // Exercise 2: every course, no filter
        System.out.println("\nAll courses:");
        courses.stream().forEach(System.out::println);

        // Exercise 3: only courses that have the word "Spring" in them
        System.out.println("\nCourses containing \"Spring\":");
        courses.stream()
                .filter(course -> course.contains("Spring"))
                .forEach(System.out::println);

        // Exercise 4: courses with AT LEAST 4 letters.
        // "at least 4" means >= 4, not > 4 - read such words carefully, it's a common mistake
        System.out.println("\nCourses with at least 4 letters:");
        courses.stream()
                .filter(course -> course.length() >= 4)
                .forEach(System.out::println);
    }
}
