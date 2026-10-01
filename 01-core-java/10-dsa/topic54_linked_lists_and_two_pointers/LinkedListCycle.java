package topic54_linked_lists_and_two_pointers;

/*
 * Linked List Cycle (LeetCode 141) - Floyd's "tortoise and hare" (kachhua aur khargosh).
 * If you keep following next, do you ever come back to a node you already visited?
 *
 * slow moves 1 step at a time, fast moves 2 steps.
 *   No cycle: fast simply reaches the end (null) first.
 *   A cycle:  both end up running round the loop, like two runners on a circular track.
 *             fast gains exactly 1 step on slow each move, so it MUST land on slow -
 *             it can never jump over it.
 * Time O(n). Space O(1): no HashSet of visited nodes needed (the follow-up asks for constant memory).
 */
public class LinkedListCycle {

	static boolean hasCycle(ListNode head) {
		ListNode slow = head, fast = head;

		while (fast != null && fast.next != null) {   // check both, because fast jumps two nodes at once
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) return true;             // the very same node object (==), not just the same value
		}
		return false;
	}

	// Run with: java -ea to switch on the checks
	public static void main(String[] args) {
		ListNode head = ListNode.of(3, 2, 0, -4);
		head.at(3).next = head.at(1);                  // pos = 1: the last node points back to the node with 2
		System.out.println(hasCycle(head));

		assert hasCycle(head);                         // Example 1

		ListNode two = ListNode.of(1, 2);
		two.at(1).next = two;                          // pos = 0: the last node points back to the first
		assert hasCycle(two);                          // Example 2

		assert !hasCycle(ListNode.of(1));              // Example 3: pos = -1, no cycle
		assert !hasCycle(ListNode.of(1, 2, 3, 4, 5));
		assert !hasCycle(null);                        // an empty list

		ListNode self = ListNode.of(7);
		self.next = self;                              // a node that points to itself
		assert hasCycle(self);
	}
}

/*
 * The problem: given head, the first node of a linked list, return true if some node can be
 * reached again by following the next arrows. In LeetCode's examples, pos is the position of the
 * node that the last node's next points to (-1 = no cycle). pos is NOT given to the method.
 * Limits: 0 to 10^4 nodes, -10^5 <= Node.val <= 10^5.
 */
