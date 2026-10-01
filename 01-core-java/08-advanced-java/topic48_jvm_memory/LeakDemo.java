package topic48_jvm_memory;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/*
 * Topic    : Memory leaks - yes, even in Java, where the garbage collector cleans up for you
 * Key idea : The garbage collector only removes objects that NOTHING points to any more.
 *            If an object is still pointed to - by a static list, a cache, a list of listeners -
 *            it can never be removed, even if your code will never use it again.
 *            That is a memory leak in Java.
 *            Like a storeroom where you keep the receipt for everything you ever bought:
 *            the safai wala can't throw anything away while its receipt is still on file.
 * Run      : java -cp out topic48_jvm_memory.LeakDemo
 * Try this : Remove the LEAKED.clear() line - the "kept" object can then never be cleaned up.
 */
public class LeakDemo {

    // a classic leak: a static list that only ever grows, and lives as long as the program
    private static final List<byte[]> LEAKED = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        byte[] temporary = new byte[1_000_000];            // about 1 MB
        byte[] kept = new byte[1_000_000];
        LEAKED.add(kept);                                  // the static list still points to this one

        // a WeakReference does NOT keep its object alive - it only lets us check whether the GC removed it
        WeakReference<byte[]> watchTemporary = new WeakReference<>(temporary);
        WeakReference<byte[]> watchKept = new WeakReference<>(kept);

        temporary = null;                                  // let go of our own arrows
        kept = null;
        collectGarbage();

        System.out.println("temporary collected? " + (watchTemporary.get() == null) + "   (unreachable: freed)");
        System.out.println("kept collected?      " + (watchKept.get() == null) + "  (the static list still refers to it)");

        LEAKED.clear();                                    // the fix: remove what you don't need any more
        collectGarbage();
        System.out.println("after clear():       " + (watchKept.get() == null));
        // System.gc() is only a REQUEST, not an order - so we ask a few times to get a reliable result.
    }

    // helper: politely ask the garbage collector to run, a few times
    private static void collectGarbage() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.gc();
            Thread.sleep(50);
        }
    }
}
