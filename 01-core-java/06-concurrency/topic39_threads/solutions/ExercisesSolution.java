package topic39_threads.solutions;

import java.util.Arrays;

// Answers for topic39_threads/Exercises.java
public class ExercisesSolution {

    static String runOnHelper() throws InterruptedException {
        String[] ranOn = new String[1];
        Runnable task = () -> ranOn[0] = Thread.currentThread().getName();
        Thread helper = new Thread(task, "helper");
        helper.start();                     // start(): a new thread does the job. task.run() would do it on main
        helper.join();                      // wait for it. Without join, ranOn[0] could still be null here
        return ranOn[0];
    }

    static int[] squaresInParallel(int[] values) throws InterruptedException {
        int[] result = new int[values.length];
        Thread[] workers = new Thread[values.length];
        for (int i = 0; i < values.length; i++) {
            int index = i;                  // a lambda needs a variable that never changes, so copy i
            workers[i] = new Thread(() -> result[index] = values[index] * values[index]);
            workers[i].start();
        }
        for (Thread worker : workers) {
            worker.join();                  // first start ALL of them, then wait for each one
        }
        return result;
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
