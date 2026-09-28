package topic54_linked_lists_and_two_pointers;

/*
 * Linked List Cycle (LeetCode 141) - Floyd's "tortoise and hare".
 * Does following next ever come back to a node already visited?
 *
 * slow moves 1 step, fast moves 2 steps.
 *   No cycle: fast reaches the end (null) first.
 *   A cycle: both end up going round it, and fast gains 1 step on slow each move,
 *            so it must land on slow; it can't jump over it.
 * Time O(n). Space O(1): no HashSet of visited nodes (the follow-up asks for constant memory).
 */
public class LinkedListCycle {

	static boolean hasCycle(ListNode head) {
		ListNode slow = head, fast = head;

		while (fast != null && fast.next != null) {   // check both: fast jumps two nodes
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) return true;             // the same node object (==), not the same value
		}
		return false;
	}

	// Run with: java -ea to enable the checks
	public static void main(String[] args) {
		ListNode head = ListNode.of(3, 2, 0, -4);
		head.at(3).next = head.at(1);                  // pos = 1: the tail links back to the node with 2
		System.out.println(hasCycle(head));

		assert hasCycle(head);                         // Example 1

		ListNode two = ListNode.of(1, 2);
		two.at(1).next = two;                          // pos = 0
		assert hasCycle(two);                          // Example 2

		assert !hasCycle(ListNode.of(1));              // Example 3: pos = -1
		assert !hasCycle(ListNode.of(1, 2, 3, 4, 5));
		assert !hasCycle(null);                        // an empty list

		ListNode self = ListNode.of(7);
		self.next = self;                              // a node that points at itself
		assert hasCycle(self);
	}
}

/*
 * Problem: given head, the head of a linked list, return true if some node can be reached again
 * by following next pointers. Internally, pos is the index of the node the tail's next points to
 * (-1 = no cycle); pos is not passed as a parameter.
 * Constraints: 0 to 10^4 nodes, -10^5 <= Node.val <= 10^5.
 */
