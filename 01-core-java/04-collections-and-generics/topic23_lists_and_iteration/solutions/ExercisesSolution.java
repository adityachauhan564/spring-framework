package topic23_lists_and_iteration.solutions;

import java.util.ArrayList;
import java.util.List;

// Solutions for topic23_lists_and_iteration/Exercises.java
public class ExercisesSolution {

    static List<String> withoutDuplicates(List<String> items) {
        List<String> result = new ArrayList<>();
        for (String item : items) {
            if (!result.contains(item)) {        // O(n) per check; topic 25's LinkedHashSet does it in O(1)
                result.add(item);
            }
        }
        return result;
    }

    static void removeNegatives(List<Integer> numbers) {
        numbers.removeIf(n -> n < 0);             // safe removal while going through the list
    }

    static void swapEnds(List<String> items) {
        if (items.size() < 2) {
            return;
        }
        int last = items.size() - 1;
        String first = items.get(0);
        items.set(0, items.get(last));
        items.set(last, first);                   // or: Collections.swap(items, 0, last)
    }

    public static void main(String[] args) {
        check(withoutDuplicates(List.of("b", "a", "b", "c", "a")).equals(List.of("b", "a", "c")), "exercise 1");

        List<Integer> numbers = new ArrayList<>(List.of(3, -1, 4, -1, -5, 9));
        removeNegatives(numbers);
        check(numbers.equals(List.of(3, 4, 9)), "exercise 2");

        List<String> letters = new ArrayList<>(List.of("x", "y", "z"));
        swapEnds(letters);
        check(letters.equals(List.of("z", "y", "x")), "exercise 3");
        List<String> single = new ArrayList<>(List.of("only"));
        swapEnds(single);
        check(single.equals(List.of("only")), "exercise 3 with one element");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
