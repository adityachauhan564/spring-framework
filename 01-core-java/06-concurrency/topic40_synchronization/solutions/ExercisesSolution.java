package topic40_synchronization.solutions;

import java.util.concurrent.atomic.AtomicInteger;

// Solutions for topic40_synchronization/Exercises.java
public class ExercisesSolution {

    public static void main(String[] args) throws InterruptedException {
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

class HitCounter {
    private final AtomicInteger hits = new AtomicInteger();   // lock-free and atomic

    void hit() {
        hits.incrementAndGet();
    }

    int total() {
        return hits.get();
    }
}

class Wallet {
    private final int id;
    private int balance;

    Wallet(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    static void transfer(Wallet from, Wallet to, int amount) {
        Wallet first = from.id < to.id ? from : to;               // every thread locks the lower id first,
        Wallet second = first == from ? to : from;                // so no circle of waiting can form
        synchronized (first) {
            synchronized (second) {
                from.balance -= amount;
                to.balance += amount;
            }
        }
    }

    synchronized int balance() {
        return balance;
    }

    int id() {
        return id;
    }
}
