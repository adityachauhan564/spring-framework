package topic15_capstone_course_analysis;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/*
 * Run      : java -ea -cp out topic15_capstone_course_analysis.CourseAnalysis
 * Key idea : each question is answered by one pipeline. Topics used:
 *            filter (03), Predicate (04), method refs (05), sorted/limit (06), matching and
 *            reduce (07), groupingBy (08), mapToInt (10), Optional (11).
 * Try this : find the category with the most students in total.
 */
public class CourseAnalysis {

    public static void main(String[] args) {
        List<Course> courses = List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 95, 18000),
                new Course("API", "Microservices", 97, 22000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("FullStack", "FullStack", 91, 14000),
                new Course("AWS", "Cloud", 92, 21000),
                new Course("Azure", "Cloud", 99, 21000),
                new Course("Docker", "Cloud", 92, 20000),
                new Course("Kubernetes", "Cloud", 91, 20000));

        // 1. matching: do ALL / ANY / NONE meet a condition?
        boolean allAbove90 = courses.stream().allMatch(c -> c.reviewScore() > 90);
        boolean anyBelow90 = courses.stream().anyMatch(c -> c.reviewScore() < 90);
        System.out.println("1. all scores > 90? " + allAbove90 + ", any < 90? " + anyBelow90);

        // 2. sort by students (most first), then by score as a tie-breaker
        Comparator<Course> byStudentsThenScore = Comparator.comparingInt(Course::noOfStudents).reversed()
                .thenComparing(Comparator.comparingInt(Course::reviewScore).reversed());
        System.out.println("2. top 3 by students: " + courses.stream()
                .sorted(byStudentsThenScore).limit(3).map(Course::name).toList());

        // 3. best rated course (max returns Optional)
        Optional<Course> best = courses.stream().max(Comparator.comparingInt(Course::reviewScore));
        System.out.println("3. best rated: " + best.map(Course::name).orElse("none"));

        // 4. totals and averages for well-rated courses
        int totalStudents = courses.stream().filter(c -> c.reviewScore() > 95).mapToInt(Course::noOfStudents).sum();
        double averageScore = courses.stream().mapToInt(Course::reviewScore).average().orElse(0);
        System.out.println("4. students in courses scored > 95: " + totalStudents
                + ", average score: " + String.format("%.2f", averageScore));

        // 5. group by category
        Map<String, List<String>> namesByCategory = courses.stream().collect(Collectors.groupingBy(
                Course::category, TreeMap::new, Collectors.mapping(Course::name, Collectors.toList())));
        System.out.println("5. courses by category: " + namesByCategory);

        Map<String, Long> countByCategory = courses.stream()
                .collect(Collectors.groupingBy(Course::category, TreeMap::new, Collectors.counting()));
        System.out.println("   count by category:   " + countByCategory);

        Map<String, Optional<Course>> bestPerCategory = courses.stream().collect(Collectors.groupingBy(
                Course::category, TreeMap::new, Collectors.maxBy(Comparator.comparingInt(Course::reviewScore))));
        bestPerCategory.forEach((category, course) ->
                System.out.println("   best in " + category + ": " + course.map(Course::name).orElse("-")));

        // 6. one comma-separated line of the Cloud courses
        String cloud = courses.stream()
                .filter(c -> c.category().equals("Cloud"))
                .map(Course::name)
                .collect(Collectors.joining(", "));
        System.out.println("6. cloud courses: " + cloud);

        assert allAbove90 && !anyBelow90;
        assert best.orElseThrow().name().equals("Azure");
        assert totalStudents == 20000 + 22000 + 25000 + 21000;
        assert countByCategory.get("Cloud") == 4;
    }
}
