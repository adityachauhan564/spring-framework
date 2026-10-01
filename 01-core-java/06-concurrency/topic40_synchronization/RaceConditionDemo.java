package topic40_synchronization;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Topic    : Race conditions, and how to fix them
 * Key idea : count++ looks like one step, but it is really THREE: read the value, add 1, write it back.
 *            If two threads read the same value at the same moment, both write back the same
 *            answer - and one update is simply lost.
 *            Like two people updating the same Excel sheet copy at the same time - one person's
 *            change gets overwritten.
 *            Two fixes:
 *              synchronized  - only one thread at a time can enter, like a single-person ATM cabin
 *              AtomicInteger - a special counter that does read + add + write as one unbreakable step
 * Run      : java -ea -cp out topic40_synchronization.RaceConditionDemo
 *            (-ea switches on the "assert" checks at the end)
 * Try this : Run it a few times - the unsafe total changes on every run.
 */
public class RaceConditionDemo {

    private static final int INCREMENTS = 100_000;

    private int unsafeCount = 0;
    private int syncCount = 0;
    private final AtomicInteger atomicCount = new AtomicInteger();

    private synchronized void incrementSync() {   // only one thread at a time may be inside this method
        syncCount++;
    }

    public static void main(String[] args) throws InterruptedException {
        RaceConditionDemo demo = new RaceConditionDemo();

        // both threads do exactly the same job, on the SAME three counters
        Runnable work = () -> {
            for (int i = 0; i < INCREMENTS; i++) {
                demo.unsafeCount++;                 // WRONG: some updates get lost
                demo.incrementSync();               // RIGHT: uses a lock
                demo.atomicCount.incrementAndGet(); // RIGHT: no lock needed
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
