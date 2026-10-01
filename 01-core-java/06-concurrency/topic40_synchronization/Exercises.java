package topic40_synchronization;

/*
 * Exercises for topic 40.
 * How to use:
 *   - Complete the classes written BELOW this one. Fill in every "TODO".
 *   - Then run:  java -cp out topic40_synchronization.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    public static void main(String[] args) throws InterruptedException {
        // 1. 4 threads, each adding 50,000 hits, must give exactly 200,000 - not one less
        HitCounter counter = new HitCounter();
        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 50_000; j++) {
                    counter.hit();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        check(counter.total() == 200_000, "exercise 1: got " + counter.total() + ", not 200000 -");

        // 2. Lots of transfers in BOTH directions at the same time.
        //    They must finish (no deadlock), and no money may appear or vanish.
        Wallet a = new Wallet(1, 1000);
        Wallet b = new Wallet(2, 1000);
        Thread there = new Thread(() -> {
            for (int i = 0; i < 1000; i++) Wallet.transfer(a, b, 1);
        });
        Thread back = new Thread(() -> {
            for (int i = 0; i < 1000; i++) Wallet.transfer(b, a, 1);
        });
        there.setDaemon(true);
        back.setDaemon(true);
        there.start();
        back.start();
        there.join(5000);                            // wait at most 5 seconds
        back.join(5000);
        check(!there.isAlive() && !back.isAlive(), "exercise 2: the transfers deadlocked");
        check(a.balance() + b.balance() == 2000, "exercise 2: money was created or lost");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. Make hit() safe when many threads call it together (use synchronized, or an AtomicInteger).
class HitCounter {
    private int hits;

    void hit() {
        hits++;                                      // TODO exercise 1: not atomic!
    }

    int total() {
        return hits;
    }
}

// 2. Make transfer() safe for many threads, WITHOUT any chance of a deadlock.
//    Hint: look at DeadlockDemo.safeTransfer.
class Wallet {
    private final int id;
    private int balance;

    Wallet(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    static void transfer(Wallet from, Wallet to, int amount) {
        from.balance -= amount;                      // TODO exercise 2: lock both wallets, in a fixed order
        to.balance += amount;
    }

    synchronized int balance() {
        return balance;
    }

    int id() {
        return id;
    }
}
