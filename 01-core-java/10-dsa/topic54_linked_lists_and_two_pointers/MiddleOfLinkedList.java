package topic54_linked_lists_and_two_pointers;

/*
 * Middle of the Linked List (LeetCode 876) - fast and slow pointers.
 * Return the middle node; with an even count there are two middles, and the SECOND one is wanted.
 *
 * fast moves twice as far as slow, so when fast runs off the end, slow is halfway.
 *   1 2 3 4 5    -> fast stops at 5 (fast.next == null), slow is at 3
 *   1 2 3 4 5 6  -> fast becomes null past 6, slow is at 4 (the second middle)
 * One pass, instead of counting the nodes and walking again. Time O(n), Space O(1).
 */
public class MiddleOfLinkedList {

	static ListNode middleNode(ListNode head) {
		ListNode slow = head, fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	// Run with: java -ea to enable the checks
	public static void main(String[] args) {
		ListNode middle = middleNode(ListNode.of(1, 2, 3, 4, 5));
		System.out.println(middle.val);

		assert middle.val == 3;
		assert middleNode(ListNode.of(1, 2, 3, 4, 5, 6)).val == 4;   // even: the second middle
		assert middleNode(ListNode.of(1)).val == 1;
		assert middleNode(ListNode.of(1, 2)).val == 2;
	}
}
