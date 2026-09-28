package topic40_synchronization;

/*
 * Topic    : volatile - making a change VISIBLE to other threads
 * Key idea : each thread may keep its own cached copy of a field. Without volatile, a worker
 *            may never see that another thread set 'running = false', and loop forever.
 *            volatile guarantees every read sees the latest write. It does NOT make count++ safe:
 *            that's still read + add + write (use synchronized or AtomicInteger, RaceConditionDemo).
 * Run      : java -cp out topic40_synchronization.VolatileFlag
 */
public class VolatileFlag {

    private static volatile boolean running = true;     // try removing volatile: it may never stop

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            long loops = 0;
            while (running) {
                loops++;
            }
            System.out.println("worker saw running = false and stopped after " + loops + " loops");
        });
        worker.start();

        Thread.sleep(200);
        running = false;                                 // main thread writes; volatile makes the worker see it
        worker.join();
        System.out.println("main: done");
    }
}
