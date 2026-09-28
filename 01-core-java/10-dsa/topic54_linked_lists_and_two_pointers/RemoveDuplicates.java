package topic54_linked_lists_and_two_pointers;

import java.util.HashSet;
import java.util.Set;

/*
 * Remove Duplicates from an unsorted linked list (Cracking the Coding Interview 2.1)
 * Key idea : walk the list once, remembering the values seen in a HashSet. When the current
 *            node's value was seen before, unlink it: previous.next = current.next.
 *            Time O(n), extra space O(n). Without a set it's O(n^2): for each node, scan the rest.
 * Run      : java -ea -cp out topic54_linked_lists_and_two_pointers.RemoveDuplicates
 */
public class RemoveDuplicates {

    static void removeDuplicates(ListNode head) {
        Set<Integer> seen = new HashSet<>();
        ListNode previous = null;
        for (ListNode current = head; current != null; current = current.next) {
            if (seen.contains(current.val)) {
                previous.next = current.next;          // skip this node: nothing points at it any more
            } else {
                seen.add(current.val);
                previous = current;                    // only move 'previous' past nodes we keep
            }
        }
    }

    static String print(ListNode head) {
        StringBuilder text = new StringBuilder();
        for (ListNode node = head; node != null; node = node.next) {
            text.append(node.val).append(" -> ");
        }
        return text.append("null").toString();
    }

    // Run with: java -ea to enable the checks
    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 3, 2, 1, 4);
        System.out.println("before: " + print(head));
        removeDuplicates(head);
        System.out.println("after:  " + print(head));

        assert print(head).equals("1 -> 2 -> 3 -> 4 -> null");
        ListNode same = ListNode.of(7, 7, 7);
        removeDuplicates(same);
        assert print(same).equals("7 -> null");
        removeDuplicates(null);                        // an empty list: nothing to do
    }
}
