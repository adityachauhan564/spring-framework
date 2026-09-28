package topic38_streams_capstone.solutions;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import topic38_streams_capstone.Course;

// Solutions for topic38_streams_capstone/Exercises.java
public class ExercisesSolution {

    static final List<Course> COURSES = List.of(
            new Course("Spring", "Framework", 98, 20000),
            new Course("Spring Boot", "Framework", 95, 18000),
            new Course("API", "Microservices", 97, 22000),
            new Course("Microservices", "Microservices", 96, 25000),
            new Course("AWS", "Cloud", 92, 21000),
            new Course("Azure", "Cloud", 99, 21000),
            new Course("Docker", "Cloud", 92, 20000));

    static Optional<String> biggestCategory(List<Course> courses) {
        return courses.stream()
                .collect(Collectors.groupingBy(Course::category, Collectors.summingInt(Course::noOfStudents)))
                .entrySet().stream()                      // a second stream, over the map's entries
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    static Map<String, Double> averageScoreByCategory(List<Course> courses) {
        return courses.stream().collect(Collectors.groupingBy(
                Course::category, TreeMap::new, Collectors.averagingInt(Course::reviewScore)));
    }

    static List<String> popularNames(List<Course> courses) {
        return courses.stream()
                .filter(course -> course.noOfStudents() > 20000)
                .map(Course::name)
                .sorted()
                .toList();
    }

    public static void main(String[] args) {
        check(biggestCategory(COURSES).orElse("").equals("Cloud"), "exercise 1");
        check(averageScoreByCategory(COURSES).toString().equals("{Cloud=94.33333333333333, Framework=96.5, Microservices=96.5}"),
                "exercise 2");
        check(popularNames(COURSES).equals(List.of("API", "AWS", "Azure", "Microservices")), "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
