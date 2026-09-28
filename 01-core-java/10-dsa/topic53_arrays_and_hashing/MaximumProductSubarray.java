package topic53_arrays_and_hashing;

/*
 * Maximum Product Subarray (LeetCode 152)
 * Find the contiguous subarray (at least one number) with the largest product.
 * Key idea : like Kadane (MaximumSubarray), but a negative number SWAPS big and small:
 *            the most negative product so far times a negative becomes the largest.
 *            So track both the max and the min product ending at each position.
 *            Time O(n), Space O(1).
 * Run      : java -ea -cp out topic53_arrays_and_hashing.MaximumProductSubarray
 */
public class MaximumProductSubarray {

    static int maxProduct(int[] nums) {
        int maxHere = nums[0];
        int minHere = nums[0];
        int best = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            int previousMax = maxHere;                  // needed after maxHere is overwritten
            maxHere = Math.max(n, Math.max(previousMax * n, minHere * n));
            minHere = Math.min(n, Math.min(previousMax * n, minHere * n));
            best = Math.max(best, maxHere);
        }
        return best;
    }

    // Run with: java -ea to enable the checks
    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        System.out.println(maxProduct(nums));           // 6, from [2, 3]

        assert maxProduct(nums) == 6;
        assert maxProduct(new int[] {-2, 3, -4}) == 24; // two negatives make a positive
        assert maxProduct(new int[] {-2, 0, -1}) == 0;  // zero resets the run
        assert maxProduct(new int[] {-5}) == -5;        // a single number
    }
}
