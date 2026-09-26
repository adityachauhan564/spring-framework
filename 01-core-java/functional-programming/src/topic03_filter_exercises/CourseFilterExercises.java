package topic03_filter_exercises;

import java.util.List;

/*
 * Topic    : filter() exercises (in28minutes FP01 exercises)
 * Key idea : filter(condition) keeps only the elements where the lambda returns true.
 * Run      : java -cp out topic03_filter_exercises.CourseFilterExercises
 * Try this : print courses that contain a space, then numbers divisible by 3.
 */
public class CourseFilterExercises {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 6, 13, 19, 27, 31);
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices",
                "System Design", "Docker", "Kubernetes");

        // Exercise 1: odd numbers
        System.out.println("Odd numbers:");
        numbers.stream()
                .filter(number -> number % 2 != 0)
                .forEach(System.out::println);

        // Exercise 2: every course
        System.out.println("\nAll courses:");
        courses.stream().forEach(System.out::println);

        // Exercise 3: courses containing "Spring"
        System.out.println("\nCourses containing \"Spring\":");
        courses.stream()
                .filter(course -> course.contains("Spring"))
                .forEach(System.out::println);

        // Exercise 4: courses with AT LEAST 4 letters (>= 4, not > 4 - read boundaries carefully)
        System.out.println("\nCourses with at least 4 letters:");
        courses.stream()
                .filter(course -> course.length() >= 4)
                .forEach(System.out::println);
    }
}
