package topic32_collectors;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * Topic    : Collectors - turning a stream into a collection or a summary
 * Key idea : collect(Collectors.xxx()) decides what shape the final result takes:
 *              toList, toSet       - a list or a set
 *              toMap               - key -> value pairs
 *              joining             - glue text together into one string
 *              groupingBy          - sort items into buckets, like sorting clothes into piles:
 *                                    shirts, trousers, socks
 *              partitioningBy      - exactly two buckets: true and false (like pass / fail)
 *              counting, averaging - numbers that summarise the data
 * Run      : java -cp out topic32_collectors.CollectorsDemo
 * Try this : Group the courses by their first letter.
 */
public class CollectorsDemo {

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "Docker", "Azure");
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        // simple collections
        List<Integer> list = numbers.stream().collect(Collectors.toList());     // a list you can change later
        Set<Integer> set = numbers.stream().collect(Collectors.toSet());        // duplicates are dropped
        System.out.println("toList:  " + list);
        System.out.println("toSet:   " + set + "   (no duplicates; order not guaranteed)");

        // joining text: separator ", ", with "[" at the start and "]" at the end
        System.out.println("joining: " + courses.stream().collect(Collectors.joining(", ", "[", "]")));

        // toMap: key -> value. If two items give the SAME key, it throws -
        // unless you also say how to merge them (the third argument)
        Map<String, Integer> lengthByCourse = courses.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));   // Function.identity() = "the item itself"
        System.out.println("toMap:   " + new TreeMap<>(lengthByCourse));

        Map<Integer, String> courseByLength = courses.stream()
                .collect(Collectors.toMap(String::length, c -> c, (first, second) -> first + "|" + second));
        System.out.println("toMap with merge: " + new TreeMap<>(courseByLength));

        // groupingBy: put items into buckets by some key (here: by length)
        Map<Integer, List<String>> byLength = courses.stream()
                .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
        System.out.println("groupingBy length:            " + byLength);

        // same buckets, but just count how many are in each
        Map<Integer, Long> countByLength = courses.stream()
                .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.counting()));
        System.out.println("groupingBy length + counting: " + countByLength);

        // partitioningBy: always exactly two buckets, true and false
        Map<Boolean, List<Integer>> evenOdd = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("partitioningBy even: " + evenOdd);

        // number summaries - summarizingInt gives count, sum, min, average and max in one go
        System.out.println("averagingInt: " + numbers.stream().collect(Collectors.averagingInt(n -> n)));
        System.out.println("summarizingInt: " + numbers.stream().collect(Collectors.summarizingInt(n -> n)));
    }
}
