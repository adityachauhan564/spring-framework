package topic41_executors_and_futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Topic    : ExecutorService - a thread pool (this is how real projects run tasks)
 * Key idea : Don't create a new Thread by hand for every job. Hand your jobs to a POOL of
 *            ready workers instead. Like a call centre: 3 agents, many calls - each free agent
 *            takes the next call from the queue. No hiring a new agent per call.
 *            - A Callable is a job that gives back a value.
 *            - submit() gives you a Future - a "token" for the answer. Future.get() waits for it,
 *              like waiting for your token number to be called.
 *            - Always shut the pool down when done. try-with-resources does it for you (Java 19+).
 * Run      : java -cp out topic41_executors_and_futures.ExecutorServiceDemo
 * Try this : Change the pool size to 1 and see the jobs run one after another.
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try (ExecutorService pool = Executors.newFixedThreadPool(3)) {      // a pool of 3 workers

            List<Future<Long>> results = new ArrayList<>();
            for (int n = 1; n <= 5; n++) {                                   // 5 jobs, but only 3 workers
                int limit = n * 1_000_000;
                Callable<Long> sumTask = () -> {                              // the job: add up 1..limit and return it
                    long sum = 0;
                    for (int i = 1; i <= limit; i++) {
                        sum += i;
                    }
                    System.out.println(Thread.currentThread().getName() + " summed 1.." + limit);
                    return sum;
                };
                results.add(pool.submit(sumTask));                            // hand over the job, keep the token
            }

            for (Future<Long> result : results) {
                System.out.println("result: " + result.get());   // waits here until that job's answer is ready
            }
        }   // close() = shutdown() + wait for any jobs still running
        System.out.println("pool closed");
    }
}
