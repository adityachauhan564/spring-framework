package topic22_multithreading;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Topic    : Race conditions and how to fix them
 * Key idea : count++ is really read + add + write. Two threads can read the same value
 *            and one update is lost. Fix with 'synchronized' or an AtomicInteger.
 * Run      : java -ea -cp out topic22_multithreading.RaceConditionDemo
 * Try this : run it a few times - the unsafe total changes every run.
 */
public class RaceConditionDemo {

    private static final int INCREMENTS = 100_000;

    private int unsafeCount = 0;
    private int syncCount = 0;
    private final AtomicInteger atomicCount = new AtomicInteger();

    private synchronized void incrementSync() {   // only one thread at a time may enter
        syncCount++;
    }

    public static void main(String[] args) throws InterruptedException {
        RaceConditionDemo demo = new RaceConditionDemo();

        Runnable work = () -> {
            for (int i = 0; i < INCREMENTS; i++) {
                demo.unsafeCount++;                 // lost updates
                demo.incrementSync();               // correct, uses a lock
                demo.atomicCount.incrementAndGet(); // correct, lock-free
            }
        };

        Thread t1 = new Thread(work);
        Thread t2 = new Thread(work);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        int expected = 2 * INCREMENTS;
        System.out.println("expected     : " + expected);
        System.out.println("unsafe       : " + demo.unsafeCount + "   (usually less - updates were lost)");
        System.out.println("synchronized : " + demo.syncCount);
        System.out.println("AtomicInteger: " + demo.atomicCount.get());

        assert demo.syncCount == expected;
        assert demo.atomicCount.get() == expected;
    }
}
