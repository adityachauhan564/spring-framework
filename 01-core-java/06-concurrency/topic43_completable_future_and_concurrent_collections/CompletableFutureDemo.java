package topic43_completable_future_and_concurrent_collections;

import java.util.concurrent.CompletableFuture;

/*
 * Topic    : CompletableFuture - writing background work as a pipeline
 * Key idea : A plain Future (topic 41) can only be waited on.
 *            A CompletableFuture lets you plan ahead, without making a thread sit and wait:
 *              thenApply     - "when the answer comes, do THIS with it"
 *              thenCombine   - "when BOTH answers come, join them like THIS"
 *              exceptionally - "if it fails, use THIS backup value instead"
 *            Like comparing a flight price on MakeMyTrip and Goibibo at the same time:
 *            you open both tabs together, not one after the other.
 * Run      : java -cp out topic43_completable_future_and_concurrent_collections.CompletableFutureDemo
 * Try this : Make fetchPrice throw an exception for "B" and watch exceptionally() give the fallback.
 */
public class CompletableFutureDemo {

    // pretend these two methods call slow remote services (300 ms each)
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

        // supplyAsync starts the work in the background RIGHT NOW - all three run at the same time
        CompletableFuture<Integer> priceA = CompletableFuture.supplyAsync(() -> fetchPrice("A"));
        CompletableFuture<Integer> priceB = CompletableFuture.supplyAsync(() -> fetchPrice("B"));
        CompletableFuture<Double> discount = CompletableFuture.supplyAsync(CompletableFutureDemo::fetchDiscount);

        CompletableFuture<String> best = priceA
                .thenCombine(priceB, Math::min)                               // two prices -> the cheaper one
                .thenCombine(discount, (price, d) -> price * (1 - d))         // then apply the discount
                .thenApply(finalPrice -> "best price after discount: " + finalPrice);   // then turn it into a message

        System.out.println(best.join());                                      // join: wait for the final answer
        System.out.println("three 300 ms calls took about " + (System.nanoTime() - start) / 1_000_000 + " ms in total");

        // errors: exceptionally() turns a failure into a backup value, so the program carries on
        CompletableFuture<Integer> failing = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new IllegalStateException("price service is down");
            }
            return 0;
        });
        int price = failing.exceptionally(error -> {
            System.out.println("recovered from: " + error.getCause().getMessage());
            return -1;                                                        // the backup value
        }).join();
        System.out.println("fallback price: " + price);
    }

    // helper: sleep for the given milliseconds
    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();       // someone asked us to stop - remember that request
        }
    }
}
