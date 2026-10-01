package topic54_linked_lists_and_two_pointers;

/*
 * Exercises for topic 54.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic54_linked_lists_and_two_pointers.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 * Handy: ListNode.of(1, 2, 3) builds a list, and RemoveDuplicates.print(head) shows one as text.
 */
public class Exercises {

    // 1. Reverse the list and return the new first node: 1 -> 2 -> 3 becomes 3 -> 2 -> 1.
    //    Walk through it once, turning each node's next arrow to point backwards.
    //    (Keep track of three things: previous, current and next.)
    static ListNode reverse(ListNode head) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Remove the n-th node counting FROM THE END, in one pass:
    //    (1 -> 2 -> 3 -> 4 -> 5, n = 2) -> 1 -> 2 -> 3 -> 5.
    //    Two pointers: first move 'fast' n steps ahead, then move both together until fast reaches the end.
    //    A dummy node placed in front of head makes removing the very first node easy.
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

    // helper: shows a list as text
    private static String show(ListNode head) {
        return RemoveDuplicates.print(head);
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
