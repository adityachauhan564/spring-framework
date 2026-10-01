package topic54_linked_lists_and_two_pointers;

/*
 * One node of a singly linked list - the same shape LeetCode uses in its linked-list problems.
 * Each node holds a value and an arrow (next) to the following node, like train coaches
 * joined one behind the other. The last coach's next is null.
 * ListNode.of(1, 2, 3) builds 1 -> 2 -> 3 and returns the first node (the "head").
 */
public class ListNode {

	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
	}

	static ListNode of(int... values) {
		ListNode dummy = new ListNode(0);          // a fake node in front, so the loop doesn't need a special case for the first node
		ListNode tail = dummy;
		for (int value : values) {
			tail.next = new ListNode(value);       // attach a new coach at the end
			tail = tail.next;
		}
		return dummy.next;                         // skip the fake node: the real list starts after it
	}

	/** The node at position index, counting from 0 (assumes that position exists). */
	ListNode at(int index) {
		ListNode node = this;
		for (int i = 0; i < index; i++) node = node.next;
		return node;
	}
}
