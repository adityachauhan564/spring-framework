package topic53_arrays_and_hashing;

import java.util.Arrays;
import java.util.List;

/*
 * Exercises for topic 53. Replace each "TODO" line with your code, then run:
 *   java -cp out topic53_arrays_and_hashing.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. Move every 0 to the end IN PLACE, keeping the order of the other numbers:
    //    [0, 1, 0, 3, 12] -> [1, 3, 12, 0, 0]. One pass, O(1) extra space (two pointers).
    static void moveZeros(int[] nums) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Group words that are anagrams of each other (same letters, different order).
    //    Return the number of groups: [eat, tea, tan, ate, nat, bat] -> 3.
    //    Hint: the sorted letters of a word are the same for all its anagrams - use them as a map key.
    static int anagramGroups(List<String> words) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. The length of the longest run of consecutive numbers, in O(n): [100, 4, 200, 1, 3, 2] -> 4 (1..4).
    //    Hint: put everything in a HashSet; only start counting at a number whose (n - 1) isn't there.
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
