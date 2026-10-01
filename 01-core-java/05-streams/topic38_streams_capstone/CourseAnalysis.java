package topic38_streams_capstone;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/*
 * Run      : java -ea -cp out topic38_streams_capstone.CourseAnalysis
 *            (-ea switches on the "assert" checks at the end)
 * Key idea : Each business question is answered by ONE pipeline - the kind of report
 *            an ed-tech company's dashboard shows. Topics used here:
 *            filter (29), Predicate (21), method references (22), sorted/limit (30),
 *            matching and reduce (31), groupingBy (32), mapToInt (34), Optional (35).
 * Try this : Find the category with the most students in total.
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

        // 1. matching: do ALL / ANY / NONE of the courses meet a condition?
        boolean allAbove90 = courses.stream().allMatch(c -> c.reviewScore() > 90);
        boolean anyBelow90 = courses.stream().anyMatch(c -> c.reviewScore() < 90);
        System.out.println("1. all scores > 90? " + allAbove90 + ", any < 90? " + anyBelow90);

        // 2. sort by number of students (most first). If equal, the higher score wins the tie
        Comparator<Course> byStudentsThenScore = Comparator.comparingInt(Course::noOfStudents).reversed()
                .thenComparing(Comparator.comparingInt(Course::reviewScore).reversed());
        System.out.println("2. top 3 by students: " + courses.stream()
                .sorted(byStudentsThenScore).limit(3).map(Course::name).toList());

        // 3. the best rated course (max gives an Optional, because the list could be empty)
        Optional<Course> best = courses.stream().max(Comparator.comparingInt(Course::reviewScore));
        System.out.println("3. best rated: " + best.map(Course::name).orElse("none"));

        // 4. totals and averages
        int totalStudents = courses.stream().filter(c -> c.reviewScore() > 95).mapToInt(Course::noOfStudents).sum();
        double averageScore = courses.stream().mapToInt(Course::reviewScore).average().orElse(0);
        System.out.println("4. students in courses scored > 95: " + totalStudents
                + ", average score: " + String.format("%.2f", averageScore));

        // 5. group the courses by category
        //    Collectors.mapping keeps only the NAME of each course in the bucket, not the whole course
        Map<String, List<String>> namesByCategory = courses.stream().collect(Collectors.groupingBy(
                Course::category, TreeMap::new, Collectors.mapping(Course::name, Collectors.toList())));
        System.out.println("5. courses by category: " + namesByCategory);

        Map<String, Long> countByCategory = courses.stream()
                .collect(Collectors.groupingBy(Course::category, TreeMap::new, Collectors.counting()));
        System.out.println("   count by category:   " + countByCategory);

        // the topper of each category
        Map<String, Optional<Course>> bestPerCategory = courses.stream().collect(Collectors.groupingBy(
                Course::category, TreeMap::new, Collectors.maxBy(Comparator.comparingInt(Course::reviewScore))));
        bestPerCategory.forEach((category, course) ->
                System.out.println("   best in " + category + ": " + course.map(Course::name).orElse("-")));

        // 6. one comma-separated line with all the Cloud courses
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
