package topic54_linked_lists_and_two_pointers;

/*
 * Exercises for topic 54. Replace each "TODO" line with your code, then run:
 *   java -cp out topic54_linked_lists_and_two_pointers.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 * ListNode.of(1, 2, 3) builds a list; RemoveDuplicates.print(head) shows one.
 */
public class Exercises {

    // 1. Reverse the list and return the new head: 1 -> 2 -> 3 becomes 3 -> 2 -> 1.
    //    Walk once, turning each node's next to point backwards (keep previous, current, next).
    static ListNode reverse(ListNode head) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Remove the n-th node FROM THE END in one pass: (1 -> 2 -> 3 -> 4 -> 5, n = 2) -> 1 -> 2 -> 3 -> 5.
    //    Two pointers: move 'fast' n steps ahead first, then move both until fast reaches the end.
    //    A dummy node in front of head makes removing the first node easy.
    static ListNode removeNthFromEnd(ListNode head, int n) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Merge two SORTED lists into one sorted list: (1 -> 3 -> 5, 2 -> 4) -> 1 -> 2 -> 3 -> 4 -> 5.
    static ListNode mergeSorted(ListNode a, ListNode b) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        check(show(reverse(ListNode.of(1, 2, 3))).equals("3 -> 2 -> 1 -> null"), "exercise 1");
        check(reverse(null) == null, "exercise 1 empty list");

        check(show(removeNthFromEnd(ListNode.of(1, 2, 3, 4, 5), 2)).equals("1 -> 2 -> 3 -> 5 -> null"), "exercise 2");
        check(show(removeNthFromEnd(ListNode.of(1, 2), 2)).equals("2 -> null"), "exercise 2 remove the head");

        check(show(mergeSorted(ListNode.of(1, 3, 5), ListNode.of(2, 4))).equals("1 -> 2 -> 3 -> 4 -> 5 -> null"),
                "exercise 3");
        System.out.println("All exercises pass");
    }

    private static String show(ListNode head) {
        return RemoveDuplicates.print(head);
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
