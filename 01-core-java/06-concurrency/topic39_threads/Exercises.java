package topic39_threads;

import java.util.Arrays;

/*
 * Exercises for topic 39. Replace each "TODO" line with your code, then run:
 *   java -cp out topic39_threads.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. Run the task on a NEW thread named "helper", wait for it, and return the name of the
    //    thread the task ran on (so the answer must be "helper", not "main").
    static String runOnHelper() throws InterruptedException {
        String[] ranOn = new String[1];
        Runnable task = () -> ranOn[0] = Thread.currentThread().getName();
        // TODO: start the task on a thread called "helper" and wait for it to finish
        return ranOn[0];
    }

    // 2. Square every value, one thread per element: thread i writes result[i].
    //    Each thread writes its OWN slot, so nothing is shared. Don't return before ALL threads are done.
    static int[] squaresInParallel(int[] values) throws InterruptedException {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    public static void main(String[] args) throws InterruptedException {
        check("helper".equals(runOnHelper()), "exercise 1 (start() a new thread, then join() it)");
        check(Arrays.equals(squaresInParallel(new int[] {1, 2, 3, 4}), new int[] {1, 4, 9, 16}), "exercise 2");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
