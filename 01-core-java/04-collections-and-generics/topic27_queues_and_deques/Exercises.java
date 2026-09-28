package topic27_queues_and_deques;

import java.util.List;

/*
 * Exercises for topic 27. Replace each "TODO" line with your code, then run:
 *   java -cp out topic27_queues_and_deques.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. Are the brackets balanced? "({[]})" -> true, "(]" -> false, "((" -> false.
    //    Push each opening bracket on a stack (ArrayDeque); a closing one must match the top.
    static boolean isBalanced(String brackets) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The k smallest values in ascending order, using a PriorityQueue: ({5, 1, 4, 2}, 2) -> [1, 2].
    static List<Integer> smallest(int[] values, int k) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Simulate a print queue (FIFO): jobs are served in arrival order. Return the order they finish in
    //    when "URGENT:" jobs jump to the FRONT of the queue. Hint: a Deque can add at both ends.
    //    [a, URGENT:b, c] -> [URGENT:b, a, c]
    static List<String> printOrder(List<String> jobs) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        check(isBalanced("({[]})") && isBalanced(""), "exercise 1 balanced");
        check(!isBalanced("(]") && !isBalanced("((") && !isBalanced("())"), "exercise 1 not balanced");
        check(smallest(new int[] {5, 1, 4, 2}, 2).equals(List.of(1, 2)), "exercise 2");
        check(printOrder(List.of("a", "URGENT:b", "c")).equals(List.of("URGENT:b", "a", "c")), "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
