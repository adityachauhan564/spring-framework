package topic43_completable_future_and_concurrent_collections;

import java.util.concurrent.CompletableFuture;

/*
 * Topic    : CompletableFuture - describe async work as a pipeline
 * Key idea : a Future (topic 41) can only be waited on. A CompletableFuture lets you say what
 *            happens NEXT (thenApply), combine two results (thenCombine) and recover from
 *            errors (exceptionally) - without blocking a thread while you wait.
 * Run      : java -cp out topic43_completable_future_and_concurrent_collections.CompletableFutureDemo
 * Try this : make fetchPrice throw for "B" and watch exceptionally() supply the fallback.
 */
public class CompletableFutureDemo {

    // pretend these call two slow remote services
    static int fetchPrice(String shop) {
        sleep(300);
        return shop.equals("A") ? 500 : 450;
    }

    static double fetchDiscount() {
        sleep(300);
        return 0.10;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();

        CompletableFuture<Integer> priceA = CompletableFuture.supplyAsync(() -> fetchPrice("A"));   // starts now
        CompletableFuture<Integer> priceB = CompletableFuture.supplyAsync(() -> fetchPrice("B"));   // in parallel
        CompletableFuture<Double> discount = CompletableFuture.supplyAsync(CompletableFutureDemo::fetchDiscount);

        CompletableFuture<String> best = priceA
                .thenCombine(priceB, Math::min)                               // both results -> one
                .thenCombine(discount, (price, d) -> price * (1 - d))
                .thenApply(finalPrice -> "best price after discount: " + finalPrice);   // transform the result

        System.out.println(best.join());                                      // join: wait for the end result
        System.out.println("three 300 ms calls took about " + (System.nanoTime() - start) / 1_000_000 + " ms in total");

        // errors: exceptionally() turns a failure into a fallback value
        CompletableFuture<Integer> failing = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new IllegalStateException("price service is down");
            }
            return 0;
        });
        int price = failing.exceptionally(error -> {
            System.out.println("recovered from: " + error.getCause().getMessage());
            return -1;
        }).join();
        System.out.println("fallback price: " + price);
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
