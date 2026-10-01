package topic53_arrays_and_hashing.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Answers for topic53_arrays_and_hashing/Exercises.java
public class ExercisesSolution {

    static void moveZeros(int[] nums) {
        int write = 0;                                  // the next free spot for a non-zero number
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                nums[write++] = nums[read];             // copy non-zeros forward - they keep their order
            }
        }
        while (write < nums.length) {
            nums[write++] = 0;                          // fill whatever is left at the end with zeros
        }
    }

    static int anagramGroups(List<String> words) {
        Map<String, Integer> groups = new HashMap<>();
        for (String word : words) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);                       // "tea" and "eat" both become "aet" - the same key
            groups.merge(new String(letters), 1, Integer::sum);
        }
        return groups.size();                           // one key per group
    }

    static int longestConsecutive(int[] nums) {
        Set<Integer> all = new HashSet<>();
        for (int n : nums) all.add(n);

        int best = 0;
        for (int n : all) {
            if (!all.contains(n - 1)) {                 // nothing just below n, so n STARTS a run: count up from it
                int length = 1;
                while (all.contains(n + length)) {
                    length++;
                }
                best = Math.max(best, length);
            }
        }
        return best;                                    // each number is looked at at most twice, so this is O(n)
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
