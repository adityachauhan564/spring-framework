package topic27_queues_and_deques;

import java.util.List;

/*
 * Exercises for topic 27.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic27_queues_and_deques.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Are the brackets balanced? "({[]})" -> true, "(]" -> false, "((" -> false.
    //    Push every opening bracket onto a stack (ArrayDeque). Every closing bracket must match the top one.
    static boolean isBalanced(String brackets) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. The k smallest values, smallest first, using a PriorityQueue: ({5, 1, 4, 2}, 2) -> [1, 2].
    static List<Integer> smallest(int[] values, int k) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Act like an office printer queue (FIFO): jobs are printed in the order they arrive.
    //    BUT "URGENT:" jobs jump straight to the FRONT of the line.
    //    Return the order in which the jobs get printed. Hint: a Deque can add at both ends.
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
