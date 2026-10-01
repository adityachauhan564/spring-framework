package topic40_synchronization;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/*
 * Topic    : Deadlock, and the fix: always take locks in the same order
 * Key idea : Thread 1 holds lock A and waits for lock B. Thread 2 holds lock B and waits for lock A.
 *            Neither can ever move. This is a deadlock.
 *            Like two cars meeting on a narrow one-lane bridge from opposite sides -
 *            each waits for the other to reverse, and nobody ever moves.
 *            The fix: every thread takes the locks in ONE agreed order
 *            (here: the account with the smaller id first). Then the circle can never form.
 * Run      : java -cp out topic40_synchronization.DeadlockDemo
 * Try this : Remove the id ordering in safeTransfer and run again.
 */
public class DeadlockDemo {

    static class Account {
        final int id;
        int balance = 100;

        Account(int id) {
            this.id = id;
        }
    }

    // WRONG: always locks 'from' first, then 'to'.
    // A->B and B->A at the same time lock in opposite orders - and get stuck
    static void unsafeTransfer(Account from, Account to, int amount) {
        synchronized (from) {
            pause();                                     // a small wait, so the bad timing happens almost every time
            synchronized (to) {
                from.balance -= amount;
                to.balance += amount;
            }
        }
    }

    // RIGHT: the account with the smaller id is ALWAYS locked first, whichever way the money goes
    static void safeTransfer(Account from, Account to, int amount) {
        Account first = from.id < to.id ? from : to;
        Account second = first == from ? to : from;
        synchronized (first) {
            pause();
            synchronized (second) {
                from.balance -= amount;
                to.balance += amount;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1);
        Account b = new Account(2);

        // daemon threads: if one gets stuck forever, it still won't stop the program from ending
        Thread t1 = daemon(() -> unsafeTransfer(a, b, 10));
        Thread t2 = daemon(() -> unsafeTransfer(b, a, 20));
        t1.start();
        t2.start();
        t1.join(1000);                                   // wait at most 1 second, not forever
        t2.join(1000);

        ThreadMXBean jvm = ManagementFactory.getThreadMXBean();
        long[] stuck = jvm.findDeadlockedThreads();      // the JVM can spot a deadlock - but it cannot fix it
        System.out.println("unsafe transfers: " + (stuck == null ? "finished (lucky timing)" : stuck.length + " threads deadlocked"));

        Account c = new Account(3);
        Account d = new Account(4);
        Thread t3 = daemon(() -> safeTransfer(c, d, 10));
        Thread t4 = daemon(() -> safeTransfer(d, c, 20));
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println("safe transfers:   finished, balances " + c.balance + " + " + d.balance + " = " + (c.balance + d.balance));
    }

    // helper: makes a daemon thread (a background thread that doesn't keep the program alive)
    private static Thread daemon(Runnable work) {
        Thread thread = new Thread(work);
        thread.setDaemon(true);
        return thread;
    }

    // helper: sleep for 100 milliseconds
    private static void pause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();          // someone asked us to stop - remember that request
        }
    }
}
