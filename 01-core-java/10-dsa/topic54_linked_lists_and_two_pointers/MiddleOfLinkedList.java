package topic54_linked_lists_and_two_pointers;

/*
 * Middle of the Linked List (LeetCode 876) - fast and slow pointers.
 * Return the middle node. If the count is even there are two middles - return the SECOND one.
 *
 * fast moves twice as far as slow. So when fast runs off the end, slow is exactly halfway.
 * Like two friends walking from Delhi to Agra - one at double speed. When the fast one reaches
 * Agra, the slow one is at the halfway point.
 *   1 2 3 4 5    -> fast stops at 5 (fast.next == null), slow is at 3
 *   1 2 3 4 5 6  -> fast goes past 6 and becomes null, slow is at 4 (the second middle)
 * Only one pass - no need to count the nodes first and then walk again. Time O(n), Space O(1).
 */
public class MiddleOfLinkedList {

	static ListNode middleNode(ListNode head) {
		ListNode slow = head, fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;                          // 1 step
			fast = fast.next.next;                     // 2 steps
		}
		return slow;
	}

	// Run with: java -ea to switch on the checks
	public static void main(String[] args) {
		ListNode middle = middleNode(ListNode.of(1, 2, 3, 4, 5));
		System.out.println(middle.val);

		assert middle.val == 3;
		assert middleNode(ListNode.of(1, 2, 3, 4, 5, 6)).val == 4;   // even count: the second middle
		assert middleNode(ListNode.of(1)).val == 1;
		assert middleNode(ListNode.of(1, 2)).val == 2;
	}
}
