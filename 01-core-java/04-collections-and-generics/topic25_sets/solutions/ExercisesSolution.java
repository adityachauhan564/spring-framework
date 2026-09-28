package topic25_sets.solutions;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// Solutions for topic25_sets/Exercises.java
public class ExercisesSolution {

    static boolean hasDuplicate(int[] values) {
        Set<Integer> seen = new HashSet<>();
        for (int v : values) {
            if (!seen.add(v)) {                  // add() is false when v was already there
                return true;
            }
        }
        return false;
    }

    static Set<String> commonWords(String first, String second) {
        Set<String> common = new TreeSet<>(List.of(first.toLowerCase().split(" ")));   // TreeSet: sorted
        common.retainAll(Set.of(second.toLowerCase().split(" ")));                     // keep only the shared ones
        return common;
    }

    static Set<String> distinctInOrder(List<String> items) {
        return new LinkedHashSet<>(items);       // no duplicates AND first-seen order
    }

    public static void main(String[] args) {
        check(hasDuplicate(new int[] {1, 5, 3, 5}) && !hasDuplicate(new int[] {1, 2, 3}), "exercise 1");
        check(commonWords("The cat sat", "the dog sat").toString().equals("[sat, the]"), "exercise 2");
        check(distinctInOrder(List.of("b", "a", "b", "c")).toString().equals("[b, a, c]"), "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
