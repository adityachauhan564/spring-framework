package topic53_arrays_and_hashing;

/*
 * Maximum Product Subarray (LeetCode 152)
 * Find the continuous part of the array (at least one number) whose numbers multiply to the biggest value.
 * Key idea : Like Kadane (MaximumSubarray), but with a twist - a negative number FLIPS big and small.
 *            The most negative product so far, multiplied by a negative, suddenly becomes the biggest!
 *            (-6 x -4 = +24.) So we track BOTH the max and the min product ending at each position.
 *            Time O(n), Space O(1).
 * Run      : java -ea -cp out topic53_arrays_and_hashing.MaximumProductSubarray
 */
public class MaximumProductSubarray {

    static int maxProduct(int[] nums) {
        int maxHere = nums[0];                          // biggest product of a group ending here
        int minHere = nums[0];                          // smallest (most negative) product ending here
        int best = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            int previousMax = maxHere;                  // save it - maxHere gets overwritten on the next line, but minHere still needs the old value
            maxHere = Math.max(n, Math.max(previousMax * n, minHere * n));
            minHere = Math.min(n, Math.min(previousMax * n, minHere * n));
            best = Math.max(best, maxHere);
        }
        return best;
    }

    // Run with: java -ea to switch on the checks
    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        System.out.println(maxProduct(nums));           // 6, from [2, 3]

        assert maxProduct(nums) == 6;
        assert maxProduct(new int[] {-2, 3, -4}) == 24; // two negatives make a positive
        assert maxProduct(new int[] {-2, 0, -1}) == 0;  // a zero wipes out the running product
        assert maxProduct(new int[] {-5}) == -5;        // just one number
    }
}
