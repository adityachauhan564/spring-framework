package topic33_flatmap;

import java.util.Arrays;
import java.util.List;

/*
 * Topic    : flatMap - one item becomes MANY items, and all of them are poured into one single stream
 * Key idea : map:     one item -> one value              [a, b] -> [A, B]
 *            flatMap: one item -> a stream of values      [[1,2],[3]] -> [1, 2, 3]
 *            Like emptying several small bags of groceries into one big basket -
 *            you no longer have bags, just all the items together.
 * Run      : java -cp out topic33_flatmap.FlatMapDemo
 * Try this : List every distinct letter used in all the course names, sorted.
 */
public class FlatMapDemo {

    public static void main(String[] args) {
        // 1. flatten a list of lists into one simple list
        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4, 5), List.of(6));
        List<Integer> flat = nested.stream()
                .flatMap(List::stream)                    // open each small bag (inner list) and pour it out
                .toList();
        System.out.println("nested: " + nested + " -> flat: " + flat);

        // map vs flatMap on sentences - see the difference in the output
        List<String> sentences = List.of("I love Java", "Streams are fun");
        System.out.println("map:     " + sentences.stream().map(s -> s.split(" ")).map(Arrays::toString).toList()
                + "   (a stream of arrays)");
        System.out.println("flatMap: " + sentences.stream().flatMap(s -> Arrays.stream(s.split(" "))).toList()
                + "   (one stream of words)");

        // 2. every distinct letter across all courses
        List<String> courses = List.of("Spring", "API", "AWS");
        List<String> letters = courses.stream()
                .flatMap(course -> course.chars().mapToObj(c -> String.valueOf((char) c)))   // each course -> a stream of its letters
                .distinct()
                .toList();
        System.out.println("distinct letters: " + letters);

        // 3. every pair from two lists - like every T-shirt size in every colour on Myntra
        List<String> sizes = List.of("S", "M");
        List<String> colors = List.of("Red", "Blue");
        List<String> combos = sizes.stream()
                .flatMap(size -> colors.stream().map(color -> size + "-" + color))   // each size -> a stream of all its colour pairs
                .toList();
        System.out.println("all combinations: " + combos);
    }
}
