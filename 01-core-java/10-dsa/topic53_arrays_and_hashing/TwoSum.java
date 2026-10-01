package topic53_arrays_and_hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * Problem  : You get a list of different whole numbers and a target. Return the two numbers
 *            that add up to the target (in any order), or an empty array if there are none.
 *            A number can't be added to itself, and there is at most one such pair.
 *            Like finding two items in a shop that together cost exactly your 10 rupees.
 * Example  : [3, -4, 8, 11, 1, -1, 6], target 10  ->  [-1, 11]
 * Run      : java -ea -cp out topic53_arrays_and_hashing.TwoSum
 *            (-ea switches on the "assert" checks)
 * Try this : Solve it a third way - sort the array, then use two pointers. That is O(n log n).
 */
public class TwoSum {

    // Way 1 - brute force: try every possible pair. O(n^2) time, no extra memory.
    static int[] twoSumBruteForce(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {   // j starts after i, so a number is never paired with itself
                if (numbers[i] + numbers[j] == target) {
                    return new int[] {numbers[i], numbers[j]};
                }
            }
        }
        return new int[0];
    }

    // Way 2 - HashSet: for each number x, ask "have I already seen (target - x)?"
    // O(n) time, but O(n) extra memory for the set. Trading memory for speed.
    static int[] twoSumHashSet(int[] numbers, int target) {
        Set<Integer> seen = new HashSet<>();                // every number we have walked past so far
        for (int x : numbers) {
            int needed = target - x;                        // the partner x is looking for
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
        assert twoSumHashSet(numbers, 100).length == 0;          // no pair exists
        assert twoSumHashSet(new int[] {5}, 10).length == 0;     // 5 + 5 is not allowed (same number twice)
        System.out.println("All TwoSum checks passed");
    }

    // helper: the pair in sorted order, so the checks don't depend on which number comes first
    private static int[] sorted(int[] pair) {
        int[] copy = pair.clone();
        Arrays.sort(copy);
        return copy;
    }
}
