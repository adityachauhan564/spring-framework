package topic54_linked_lists_and_two_pointers;

/*
 * A singly linked list node, as used by LeetCode's linked-list problems.
 * ListNode.of(1, 2, 3) builds 1 -> 2 -> 3 and returns the head.
 */
public class ListNode {

	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
	}

	static ListNode of(int... values) {
		ListNode dummy = new ListNode(0);          // a placeholder in front, so the loop needs no special first case
		ListNode tail = dummy;
		for (int value : values) {
			tail.next = new ListNode(value);
			tail = tail.next;
		}
		return dummy.next;
	}

	/** The node at 0-based position index (assumes it exists). */
	ListNode at(int index) {
		ListNode node = this;
		for (int i = 0; i < index; i++) node = node.next;
		return node;
	}
}
