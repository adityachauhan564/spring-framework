package topic25_sets.solutions;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// Answers for topic25_sets/Exercises.java
public class ExercisesSolution {

    static boolean hasDuplicate(int[] values) {
        Set<Integer> seen = new HashSet<>();     // every value we have met so far
        for (int v : values) {
            if (!seen.add(v)) {                  // add() gives false when v was already there - a duplicate!
                return true;
            }
        }
        return false;
    }

    static Set<String> commonWords(String first, String second) {
        Set<String> common = new TreeSet<>(List.of(first.toLowerCase().split(" ")));   // TreeSet keeps them sorted
        common.retainAll(Set.of(second.toLowerCase().split(" ")));                     // keep only the words both sentences share
        return common;
    }

    static Set<String> distinctInOrder(List<String> items) {
        return new LinkedHashSet<>(items);       // removes duplicates AND remembers the order they first came in
    }

    public static void main(String[] args) {
        check(hasDuplicate(new int[] {1, 5, 3, 5}) && !hasDuplicate(new int[] {1, 2, 3}), "exercise 1");
        check(commonWords("The cat sat", "the dog sat").toString().equals("[sat, the]"), "exercise 2");
        check(distinctInOrder(List.of("b", "a", "b", "c")).toString().equals("[b, a, c]"), "exercise 3");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
