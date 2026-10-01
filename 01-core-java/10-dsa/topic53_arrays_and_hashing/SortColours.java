package topic53_arrays_and_hashing;

import java.util.Arrays;

/*
 * Sort Colors (LeetCode 75) - the Dutch National Flag algorithm (by Dijkstra).
 * The array has only 0s, 1s and 2s. Sort it in place, in ONE pass, without any sort library.
 * Like sorting a heap of red, white and green kanche (marbles) into three piles in one go.
 *
 * Three pointers divide the array into four areas:
 *   [0, low)    all 0s              [low, mid)   all 1s
 *   [mid, high] not checked yet     (high, end]  all 2s
 * Look at nums[mid]:
 *   0 -> swap it into the 0s area. Both low and mid move right.
 *   1 -> it's already in the right place. Only mid moves right.
 *   2 -> swap it into the 2s area. Only high moves left - because the value that just
 *        came over from high hasn't been checked yet.
 * Time O(n), one pass. Space O(1).
 */
public class SortColours {

	static void sortColours(int[] nums) {
		int low = 0, mid = 0, high = nums.length - 1;

		while (mid <= high) {                           // stop once every value has been checked
			switch (nums[mid]) {
				case 0 -> swap(nums, low++, mid++);
				case 1 -> mid++;
				case 2 -> swap(nums, mid, high--);
				default -> throw new IllegalArgumentException("Only 0, 1 and 2 allowed, got " + nums[mid]);
			}
		}
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	// Run with: java -ea to switch on the checks
	public static void main(String[] args) {
		int[] nums = { 2, 0, 2, 1, 1, 0 };
		sortColours(nums);
		System.out.println(Arrays.toString(nums));

		assert Arrays.equals(nums, new int[] { 0, 0, 1, 1, 2, 2 });
		assert sorted(new int[] { 2, 0, 1 }, new int[] { 0, 1, 2 });
		assert sorted(new int[] { 2, 2, 2 }, new int[] { 2, 2, 2 });   // high crosses mid right away
		assert sorted(new int[] { 0 }, new int[] { 0 });
		assert sorted(new int[] {}, new int[] {});
	}

	// helper: sorts the input and says whether it matches what we expected
	private static boolean sorted(int[] input, int[] expected) {
		sortColours(input);
		return Arrays.equals(input, expected);
	}
}
