package topic40_synchronization;

/*
 * Exercises for topic 40. Complete the classes below this one, then run:
 *   java -cp out topic40_synchronization.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    public static void main(String[] args) throws InterruptedException {
        // 1. 4 threads x 50,000 increments must give exactly 200,000
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

        // 2. Many transfers in BOTH directions at once: must finish (no deadlock) and keep the total
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
        there.join(5000);
        back.join(5000);
        check(!there.isAlive() && !back.isAlive(), "exercise 2: the transfers deadlocked");
        check(a.balance() + b.balance() == 2000, "exercise 2: money was created or lost");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. Make hit() safe for many threads (synchronized, or an AtomicInteger).
class HitCounter {
    private int hits;

    void hit() {
        hits++;                                      // TODO exercise 1: not atomic!
    }

    int total() {
        return hits;
    }
}

// 2. Make transfer() thread-safe WITHOUT risking a deadlock. Hint: DeadlockDemo.safeTransfer.
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
