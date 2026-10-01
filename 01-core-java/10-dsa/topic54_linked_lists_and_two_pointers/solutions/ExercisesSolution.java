package topic54_linked_lists_and_two_pointers.solutions;

// Answers for topic54_linked_lists_and_two_pointers/Exercises.java
// (This file has its own copy of the small node class, because ListNode's fields
//  are package-private and can't be seen from this "solutions" package.)
public class ExercisesSolution {

    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }

        static Node of(int... values) {
            Node dummy = new Node(0);
            Node tail = dummy;
            for (int v : values) {
                tail.next = new Node(v);
                tail = tail.next;
            }
            return dummy.next;
        }
    }

    static Node reverse(Node head) {
        Node previous = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;          // remember the rest of the list BEFORE we cut the link
            current.next = previous;           // turn this arrow to point backwards
            previous = current;                // step forward
            current = next;
        }
        return previous;                       // the old last node is the new first node
    }

    static Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0);              // a fake node in front of head
        dummy.next = head;
        Node fast = dummy;
        Node slow = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;                  // now fast is n nodes ahead of slow
        }
        while (fast.next != null) {            // move both, keeping the same gap
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;            // slow is just before the node to remove - skip over it
        return dummy.next;                     // thanks to the dummy, removing the first node works too
    }

    static Node mergeSorted(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;
        while (a != null && b != null) {       // like merging two sorted queues: always take the smaller front
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = a != null ? a : b;         // one list is finished: attach whatever is left of the other
        return dummy.next;
    }

    public static void main(String[] args) {
        check(show(reverse(Node.of(1, 2, 3))).equals("3 -> 2 -> 1 -> null"), "exercise 1");
        check(reverse(null) == null, "exercise 1 empty list");

        check(show(removeNthFromEnd(Node.of(1, 2, 3, 4, 5), 2)).equals("1 -> 2 -> 3 -> 5 -> null"), "exercise 2");
        check(show(removeNthFromEnd(Node.of(1, 2), 2)).equals("2 -> null"), "exercise 2 remove the head");

        check(show(mergeSorted(Node.of(1, 3, 5), Node.of(2, 4))).equals("1 -> 2 -> 3 -> 4 -> 5 -> null"),
                "exercise 3");
        System.out.println("All exercises pass");
    }

    // helper: shows a list as text like "1 -> 2 -> null"
    private static String show(Node head) {
        StringBuilder text = new StringBuilder();
        for (Node node = head; node != null; node = node.next) {
            text.append(node.val).append(" -> ");
        }
        return text.append("null").toString();
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
