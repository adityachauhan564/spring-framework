package topic40_synchronization;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/*
 * Topic    : Deadlock, and the fix: always take locks in the same order
 * Key idea : thread 1 holds lock A and waits for B; thread 2 holds B and waits for A.
 *            Neither can continue, ever. If every thread takes the locks in ONE agreed order
 *            (here: the lower account id first), the circle can't form.
 * Run      : java -cp out topic40_synchronization.DeadlockDemo
 * Try this : remove the id ordering in safeTransfer and run again.
 */
public class DeadlockDemo {

    static class Account {
        final int id;
        int balance = 100;

        Account(int id) {
            this.id = id;
        }
    }

    // WRONG: locks 'from' then 'to' - two opposite transfers lock in opposite orders
    static void unsafeTransfer(Account from, Account to, int amount) {
        synchronized (from) {
            pause();                                     // makes the bad timing near-certain
            synchronized (to) {
                from.balance -= amount;
                to.balance += amount;
            }
        }
    }

    // RIGHT: the account with the smaller id is always locked first
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

        // daemon threads: a stuck daemon thread doesn't stop the program from ending
        Thread t1 = daemon(() -> unsafeTransfer(a, b, 10));
        Thread t2 = daemon(() -> unsafeTransfer(b, a, 20));
        t1.start();
        t2.start();
        t1.join(1000);                                   // wait at most 1 second
        t2.join(1000);

        ThreadMXBean jvm = ManagementFactory.getThreadMXBean();
        long[] stuck = jvm.findDeadlockedThreads();      // the JVM can detect it - but not undo it
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

    private static Thread daemon(Runnable work) {
        Thread thread = new Thread(work);
        thread.setDaemon(true);
        return thread;
    }

    private static void pause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
