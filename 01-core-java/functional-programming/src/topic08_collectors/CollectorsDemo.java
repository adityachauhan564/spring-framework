package topic08_collectors;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * Topic    : Collectors - turning a stream into a collection or a summary
 * Key idea : collect(Collectors.xxx()) builds the result: toList, toSet, toMap, joining,
 *            groupingBy (into buckets), partitioningBy (true/false), counting, averaging.
 * Run      : java -cp out topic08_collectors.CollectorsDemo
 * Try this : group the courses by their first letter.
 */
public class CollectorsDemo {

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "Docker", "Azure");
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        // simple collections
        List<Integer> list = numbers.stream().collect(Collectors.toList());     // modifiable
        Set<Integer> set = numbers.stream().collect(Collectors.toSet());        // removes duplicates
        System.out.println("toList:  " + list);
        System.out.println("toSet:   " + set + "   (no duplicates; order not guaranteed)");

        // joining strings
        System.out.println("joining: " + courses.stream().collect(Collectors.joining(", ", "[", "]")));

        // toMap: key -> value. Duplicate keys throw, unless you pass a merge function.
        Map<String, Integer> lengthByCourse = courses.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println("toMap:   " + new TreeMap<>(lengthByCourse));

        Map<Integer, String> courseByLength = courses.stream()
                .collect(Collectors.toMap(String::length, c -> c, (first, second) -> first + "|" + second));
        System.out.println("toMap with merge: " + new TreeMap<>(courseByLength));

        // groupingBy: buckets by a key
        Map<Integer, List<String>> byLength = courses.stream()
                .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
        System.out.println("groupingBy length:            " + byLength);

        Map<Integer, Long> countByLength = courses.stream()
                .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.counting()));
        System.out.println("groupingBy length + counting: " + countByLength);

        // partitioningBy: exactly two buckets, true and false
        Map<Boolean, List<Integer>> evenOdd = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("partitioningBy even: " + evenOdd);

        // numeric summaries
        System.out.println("averagingInt: " + numbers.stream().collect(Collectors.averagingInt(n -> n)));
        System.out.println("summarizingInt: " + numbers.stream().collect(Collectors.summarizingInt(n -> n)));
    }
}
