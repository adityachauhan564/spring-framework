package chapter_1_arrays_and_strings;

import java.util.Arrays;

/*
 * Sort Colors (LeetCode 75) - Dutch National Flag algorithm (Dijkstra).
 * The array holds only 0, 1 and 2. Sort it in place, in one pass, without a sort library.
 *
 * Three pointers split the array into four zones:
 *   [0, low)    all 0s          [low, mid)   all 1s
 *   [mid, high] not looked at   (high, end]  all 2s
 * Look at nums[mid]:
 *   0 -> swap it into the 0s zone; low and mid both move right
 *   1 -> already in place; mid moves right
 *   2 -> swap it into the 2s zone; only high moves left, because the value swapped
 *        in from high hasn't been looked at yet
 * Time O(n), one pass. Space O(1).
 */
public class SortColours {

	static void sortColours(int[] nums) {
		int low = 0, mid = 0, high = nums.length - 1;

		while (mid <= high) {
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

	// Run with: java -ea to enable the checks
	public static void main(String[] args) {
		int[] nums = { 2, 0, 2, 1, 1, 0 };
		sortColours(nums);
		System.out.println(Arrays.toString(nums));

		assert Arrays.equals(nums, new int[] { 0, 0, 1, 1, 2, 2 });
		assert sorted(new int[] { 2, 0, 1 }, new int[] { 0, 1, 2 });
		assert sorted(new int[] { 2, 2, 2 }, new int[] { 2, 2, 2 });   // high passes mid at once
		assert sorted(new int[] { 0 }, new int[] { 0 });
		assert sorted(new int[] {}, new int[] {});
	}

	private static boolean sorted(int[] input, int[] expected) {
		sortColours(input);
		return Arrays.equals(input, expected);
	}
}
