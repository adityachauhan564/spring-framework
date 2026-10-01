package topic39_threads;

import java.util.Arrays;

/*
 * Exercises for topic 39.
 * How to use:
 *   - Replace each "TODO" with your own code.
 *   - Then run:  java -cp out topic39_threads.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Run the task on a NEW thread called "helper", wait for it to finish, and return the
    //    name of the thread the task ran on. So the answer must be "helper", not "main".
    static String runOnHelper() throws InterruptedException {
        String[] ranOn = new String[1];
        Runnable task = () -> ranOn[0] = Thread.currentThread().getName();
        // TODO: start the task on a thread called "helper" and wait for it to finish
        return ranOn[0];
    }

    // 2. Square every value, using one thread per value: thread i writes result[i].
    //    Each thread writes only its OWN box, so nothing is shared between them.
    //    Don't return until ALL the threads have finished.
    static int[] squaresInParallel(int[] values) throws InterruptedException {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    public static void main(String[] args) throws InterruptedException {
        check("helper".equals(runOnHelper()), "exercise 1 (start() a new thread, then join() it)");
        check(Arrays.equals(squaresInParallel(new int[] {1, 2, 3, 4}), new int[] {1, 4, 9, 16}), "exercise 2");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
