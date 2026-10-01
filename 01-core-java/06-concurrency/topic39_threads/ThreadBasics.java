package topic39_threads;

/*
 * Topic    : Threads - doing more than one job at the same time
 * Key idea : A thread is like one worker in a kitchen. One worker makes the dal while
 *            another makes the rotis - both at the same time.
 *            - Give a Thread a Runnable (the job to do - a lambda works), then call start().
 *              NOT run() - run() just does the job on the current thread, no new worker.
 *            - join() = wait here until that worker is finished.
 *            - The order in which two threads print can be different every time you run it.
 * Run      : java -cp out topic39_threads.ThreadBasics
 * Next     : topic 40 (what goes wrong when threads share the same data)
 */
public class ThreadBasics {

    public static void main(String[] args) throws InterruptedException {
        // the job: print 3 steps, along with the name of the thread doing it
        Runnable task = () -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + " step " + i);
            }
        };

        Thread worker1 = new Thread(task, "worker-1");      // two workers, same job, different names
        Thread worker2 = new Thread(task, "worker-2");

        worker1.start();          // start() = a NEW thread runs the job. run() would just run it on 'main'
        worker2.start();

        worker1.join();           // main waits here until worker-1 is done...
        worker2.join();           // ...and worker-2

        System.out.println(Thread.currentThread().getName() + ": both workers finished");
    }
}
