package topic40_synchronization;

/*
 * Topic    : volatile - making sure other threads SEE a change
 * Key idea : Each thread may keep its own private copy of a field, for speed.
 *            Without volatile, the worker may keep looking at its OLD copy and never notice that
 *            main set 'running = false' - so it loops forever.
 *            Like a WhatsApp group where one member has notifications off - the message
 *            "meeting cancelled" was sent, but he never sees it and keeps waiting.
 *            volatile = "always read the latest value, never an old copy".
 *            But volatile does NOT make count++ safe - that is still read + add + write.
 *            For that, use synchronized or AtomicInteger (see RaceConditionDemo).
 * Run      : java -cp out topic40_synchronization.VolatileFlag
 */
public class VolatileFlag {

    private static volatile boolean running = true;     // try removing volatile: the worker may never stop

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            long loops = 0;
            while (running) {                            // keeps checking the flag
                loops++;
            }
            System.out.println("worker saw running = false and stopped after " + loops + " loops");
        });
        worker.start();

        Thread.sleep(200);                               // let the worker run for a moment
        running = false;                                 // main changes the flag. volatile makes sure the worker sees it
        worker.join();
        System.out.println("main: done");
    }
}
