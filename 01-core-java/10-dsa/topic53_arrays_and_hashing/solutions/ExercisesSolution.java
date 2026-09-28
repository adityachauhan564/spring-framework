package topic53_arrays_and_hashing.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Solutions for topic53_arrays_and_hashing/Exercises.java
public class ExercisesSolution {

    static void moveZeros(int[] nums) {
        int write = 0;                                  // next slot for a non-zero number
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                nums[write++] = nums[read];             // non-zeros keep their order
            }
        }
        while (write < nums.length) {
            nums[write++] = 0;                          // fill the rest with zeros
        }
    }

    static int anagramGroups(List<String> words) {
        Map<String, Integer> groups = new HashMap<>();
        for (String word : words) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);                       // "tea" and "eat" both become "aet"
            groups.merge(new String(letters), 1, Integer::sum);
        }
        return groups.size();
    }

    static int longestConsecutive(int[] nums) {
        Set<Integer> all = new HashSet<>();
        for (int n : nums) all.add(n);

        int best = 0;
        for (int n : all) {
            if (!all.contains(n - 1)) {                 // n starts a run: count upwards from it
                int length = 1;
                while (all.contains(n + length)) {
                    length++;
                }
                best = Math.max(best, length);
            }
        }
        return best;                                    // each number is counted at most twice: O(n)
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
