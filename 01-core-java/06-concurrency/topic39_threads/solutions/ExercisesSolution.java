package topic39_threads.solutions;

import java.util.Arrays;

// Solutions for topic39_threads/Exercises.java
public class ExercisesSolution {

    static String runOnHelper() throws InterruptedException {
        String[] ranOn = new String[1];
        Runnable task = () -> ranOn[0] = Thread.currentThread().getName();
        Thread helper = new Thread(task, "helper");
        helper.start();                     // start(): a new thread. task.run() would run on main.
        helper.join();                      // without join, ranOn[0] may still be null here
        return ranOn[0];
    }

    static int[] squaresInParallel(int[] values) throws InterruptedException {
        int[] result = new int[values.length];
        Thread[] workers = new Thread[values.length];
        for (int i = 0; i < values.length; i++) {
            int index = i;                  // a lambda needs an effectively final copy of i
            workers[i] = new Thread(() -> result[index] = values[index] * values[index]);
            workers[i].start();
        }
        for (Thread worker : workers) {
            worker.join();                  // start them ALL first, then wait for each
        }
        return result;
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
