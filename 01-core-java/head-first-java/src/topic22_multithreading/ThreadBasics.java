package topic22_multithreading;

/*
 * Topic    : Threads - running work at the same time
 * Key idea : give a Thread a Runnable (a lambda works), call start() - NOT run().
 *            join() waits for a thread to finish. Output order between threads varies.
 * Run      : java -cp out topic22_multithreading.ThreadBasics
 * Next     : RaceConditionDemo, then ExecutorServiceDemo
 */
public class ThreadBasics {

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + " step " + i);
            }
        };

        Thread worker1 = new Thread(task, "worker-1");
        Thread worker2 = new Thread(task, "worker-2");

        worker1.start();          // start() = new thread. run() would just call it on 'main'.
        worker2.start();

        worker1.join();           // main waits here until both are done
        worker2.join();

        System.out.println(Thread.currentThread().getName() + ": both workers finished");
    }
}
