package topic53_arrays_and_hashing;

/*
 * Maximum Subarray (LeetCode 53) - Kadane's algorithm.
 * Find the continuous part of the array with the biggest sum.
 * At each position, decide: "join the running group, or start a fresh group from here?"
 * If the running total has gone negative, it only pulls you down - so start fresh.
 * Like a cricket batsman's best scoring streak: a bad patch is dropped, and you start counting again.
 * Keep track of the best sum seen so far.
 * Time O(n), Space O(1).
 */
public class MaximumSubarray {

	public static int findMaxSubArraySum(int[] arr) {
		int current = arr[0];                           // best sum of a group that ENDS here
		int best = arr[0];                              // best sum found anywhere so far

		for (int i = 1; i < arr.length; i++) {
			current = Math.max(arr[i], current + arr[i]);   // start fresh, or add on to the running group?
			best = Math.max(best, current);
		}
		return best;
	}

	// Run with: java -ea to switch on the checks
	public static void main(String[] args) {
		int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		int res = findMaxSubArraySum(arr);
		System.out.println(res);

		assert res == 6;                                          // from [4,-1,2,1]
		assert findMaxSubArraySum(new int[] { -3, -1, -2 }) == -1; // all negative: the least bad single number
		assert findMaxSubArraySum(new int[] { 5 }) == 5;
	}

}
