package topic48_jvm_memory;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/*
 * Topic    : Memory leaks in a garbage-collected language
 * Key idea : the garbage collector frees objects that are UNREACHABLE. An object that is still
 *            referenced - by a static list, a cache, a listener registry - can never be freed,
 *            even if your code will never use it again. That's a Java memory leak.
 * Run      : java -cp out topic48_jvm_memory.LeakDemo
 * Try this : remove the LEAKED.clear() line - the "kept" object can then never be collected.
 */
public class LeakDemo {

    // a classic leak: a static collection that only ever grows
    private static final List<byte[]> LEAKED = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        byte[] temporary = new byte[1_000_000];
        byte[] kept = new byte[1_000_000];
        LEAKED.add(kept);                                  // still reachable through the static list

        // a WeakReference doesn't keep its object alive: it lets us watch the GC work
        WeakReference<byte[]> watchTemporary = new WeakReference<>(temporary);
        WeakReference<byte[]> watchKept = new WeakReference<>(kept);

        temporary = null;                                  // drop our references
        kept = null;
        collectGarbage();

        System.out.println("temporary collected? " + (watchTemporary.get() == null) + "   (unreachable: freed)");
        System.out.println("kept collected?      " + (watchKept.get() == null) + "  (the static list still refers to it)");

        LEAKED.clear();                                    // the fix: remove what you no longer need
        collectGarbage();
        System.out.println("after clear():       " + (watchKept.get() == null));
        // System.gc() is only a request; the demo retries so the result is reliable.
    }

    private static void collectGarbage() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.gc();
            Thread.sleep(50);
        }
    }
}
