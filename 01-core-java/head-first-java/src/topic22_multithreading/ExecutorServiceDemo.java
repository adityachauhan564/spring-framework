package topic22_multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Topic    : ExecutorService - a thread pool (how real code runs tasks)
 * Key idea : submit tasks to a pool instead of creating Threads by hand.
 *            A Callable returns a value; Future.get() waits for it.
 *            Always shut the pool down (try-with-resources does it, Java 19+).
 * Run      : java -cp out topic22_multithreading.ExecutorServiceDemo
 * Try this : change the pool size to 1 and see the tasks run one after another.
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try (ExecutorService pool = Executors.newFixedThreadPool(3)) {

            List<Future<Long>> results = new ArrayList<>();
            for (int n = 1; n <= 5; n++) {
                int limit = n * 1_000_000;
                Callable<Long> sumTask = () -> {
                    long sum = 0;
                    for (int i = 1; i <= limit; i++) {
                        sum += i;
                    }
                    System.out.println(Thread.currentThread().getName() + " summed 1.." + limit);
                    return sum;
                };
                results.add(pool.submit(sumTask));
            }

            for (Future<Long> result : results) {
                System.out.println("result: " + result.get());   // blocks until that task is done
            }
        }   // close() = shutdown() + wait for running tasks
        System.out.println("pool closed");
    }
}
