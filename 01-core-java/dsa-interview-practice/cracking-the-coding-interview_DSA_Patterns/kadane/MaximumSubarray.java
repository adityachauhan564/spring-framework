package kadane;

/*
 * Maximum Subarray (LeetCode 53) - Kadane's algorithm.
 * At each index, either extend the previous subarray or start fresh here,
 * and remember the best sum seen so far.
 * Time O(n), Space O(1).
 */
public class MaximumSubarray {

	public static int findMaxSubArraySum(int[] arr) {
		int current = arr[0];
		int best = arr[0];

		for (int i = 1; i < arr.length; i++) {
			current = Math.max(arr[i], current + arr[i]);
			best = Math.max(best, current);
		}
		return best;
	}

	// Run with: java -ea to enable the checks
	public static void main(String[] args) {
		int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		int res = findMaxSubArraySum(arr);
		System.out.println(res);

		assert res == 6;                                          // [4,-1,2,1]
		assert findMaxSubArraySum(new int[] { -3, -1, -2 }) == -1; // all negative
		assert findMaxSubArraySum(new int[] { 5 }) == 5;
	}

}
