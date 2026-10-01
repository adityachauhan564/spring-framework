package topic38_streams_capstone;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 * Exercises for topic 38.
 * How to use:
 *   - Answer each question about the courses with ONE pipeline. Delete the "TODO" line.
 *   - Then run:  java -cp out topic38_streams_capstone.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    static final List<Course> COURSES = List.of(
            new Course("Spring", "Framework", 98, 20000),
            new Course("Spring Boot", "Framework", 95, 18000),
            new Course("API", "Microservices", 97, 22000),
            new Course("Microservices", "Microservices", 96, 25000),
            new Course("AWS", "Cloud", 92, 21000),
            new Course("Azure", "Cloud", 99, 21000),
            new Course("Docker", "Cloud", 92, 20000));

    // 1. The category with the most students in total.
    //    (First add up the students for each category, then pick the biggest.)
    static Optional<String> biggestCategory(List<Course> courses) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The average review score for each category, with the categories sorted.
    static Map<String, Double> averageScoreByCategory(List<Course> courses) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Names of the courses with more than 20000 students, sorted by name.
    static List<String> popularNames(List<Course> courses) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        check(biggestCategory(COURSES).orElse("").equals("Cloud"), "exercise 1");   // Cloud has 62000 students
        check(averageScoreByCategory(COURSES).toString().equals("{Cloud=94.33333333333333, Framework=96.5, Microservices=96.5}"),
                "exercise 2");
        check(popularNames(COURSES).equals(List.of("API", "AWS", "Azure", "Microservices")), "exercise 3");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
