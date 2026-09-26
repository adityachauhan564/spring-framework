package topic09_flatmap;

import java.util.Arrays;
import java.util.List;

/*
 * Topic    : flatMap - one element becomes MANY, then everything is flattened into one stream
 * Key idea : map:     element -> value           [a, b] -> [A, B]
 *            flatMap: element -> stream of values [[1,2],[3]] -> [1, 2, 3]
 * Run      : java -cp out topic09_flatmap.FlatMapDemo
 * Try this : list every distinct letter used in all course names, sorted.
 */
public class FlatMapDemo {

    public static void main(String[] args) {
        // 1. flatten a list of lists
        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4, 5), List.of(6));
        List<Integer> flat = nested.stream()
                .flatMap(List::stream)                    // each inner list becomes a stream
                .toList();
        System.out.println("nested: " + nested + " -> flat: " + flat);

        // map vs flatMap on words
        List<String> sentences = List.of("I love Java", "Streams are fun");
        System.out.println("map:     " + sentences.stream().map(s -> s.split(" ")).map(Arrays::toString).toList()
                + "   (a stream of arrays)");
        System.out.println("flatMap: " + sentences.stream().flatMap(s -> Arrays.stream(s.split(" "))).toList()
                + "   (one stream of words)");

        // 2. distinct characters across all courses
        List<String> courses = List.of("Spring", "API", "AWS");
        List<String> letters = courses.stream()
                .flatMap(course -> course.chars().mapToObj(c -> String.valueOf((char) c)))
                .distinct()
                .toList();
        System.out.println("distinct letters: " + letters);

        // 3. all pairs from two lists (tuples)
        List<String> sizes = List.of("S", "M");
        List<String> colors = List.of("Red", "Blue");
        List<String> combos = sizes.stream()
                .flatMap(size -> colors.stream().map(color -> size + "-" + color))
                .toList();
        System.out.println("all combinations: " + combos);
    }
}
