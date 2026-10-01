package topic53_arrays_and_hashing;

import java.util.Arrays;
import java.util.List;

/*
 * Exercises for topic 53.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic53_arrays_and_hashing.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Move every 0 to the end, INSIDE the same array, keeping the other numbers in their order:
    //    [0, 1, 0, 3, 12] -> [1, 3, 12, 0, 0]. Only one pass, and no extra array (use two pointers).
    static void moveZeros(int[] nums) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Group words that are anagrams of each other (same letters, in a different order).
    //    Return how many groups there are: [eat, tea, tan, ate, nat, bat] -> 3.
    //    Hint: when you sort the letters of a word, all its anagrams give the same result -
    //    use that as a map key.
    static int anagramGroups(List<String> words) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. The length of the longest run of numbers that come one after another, in O(n):
    //    [100, 4, 200, 1, 3, 2] -> 4 (because 1, 2, 3, 4).
    //    Hint: put everything in a HashSet. Only start counting from a number n when (n - 1) is NOT in the set.
    static int longestConsecutive(int[] nums) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeros(nums);
        check(Arrays.equals(nums, new int[] {1, 3, 12, 0, 0}), "exercise 1");

        check(anagramGroups(List.of("eat", "tea", "tan", "ate", "nat", "bat")) == 3, "exercise 2");
        check(longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}) == 4, "exercise 3");
        check(longestConsecutive(new int[] {}) == 0, "exercise 3 empty");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
