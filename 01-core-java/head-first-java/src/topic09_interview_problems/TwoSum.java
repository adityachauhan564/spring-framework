package topic09_interview_problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * Problem  : Given distinct integers and a target sum, return the two numbers that
 *            add up to the target (any order), or an empty array if none exist.
 *            A number can't be added to itself. At most one pair exists.
 * Example  : [3, -4, 8, 11, 1, -1, 6], target 10  ->  [-1, 11]
 * Run      : java -ea -cp out topic09_interview_problems.TwoSum
 * Try this : solve it a third way - sort the array, then use two pointers. O(n log n).
 */
public class TwoSum {

    // Approach 1 - brute force: try every pair. O(n^2) time, O(1) extra space.
    static int[] twoSumBruteForce(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {   // j > i: never pair a number with itself
                if (numbers[i] + numbers[j] == target) {
                    return new int[] {numbers[i], numbers[j]};
                }
            }
        }
        return new int[0];
    }

    // Approach 2 - HashSet: for each x, have we already seen (target - x)? O(n) time, O(n) space.
    static int[] twoSumHashSet(int[] numbers, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int x : numbers) {
            int needed = target - x;
            if (seen.contains(needed)) {
                return new int[] {needed, x};
            }
            seen.add(x);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] numbers = {3, -4, 8, 11, 1, -1, 6};
        int target = 10;

        System.out.println("Brute force: " + Arrays.toString(twoSumBruteForce(numbers, target)));
        System.out.println("HashSet:     " + Arrays.toString(twoSumHashSet(numbers, target)));

        assert Arrays.equals(sorted(twoSumBruteForce(numbers, target)), new int[] {-1, 11});
        assert Arrays.equals(sorted(twoSumHashSet(numbers, target)), new int[] {-1, 11});
        assert twoSumHashSet(numbers, 100).length == 0;          // no pair
        assert twoSumHashSet(new int[] {5}, 10).length == 0;     // 5 + 5 not allowed
        System.out.println("All TwoSum checks passed");
    }

    private static int[] sorted(int[] pair) {
        int[] copy = pair.clone();
        Arrays.sort(copy);
        return copy;
    }
}
