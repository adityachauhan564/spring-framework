package topic48_jvm_memory;

/*
 * Topic    : Where your data lives: the stack and the heap
 * Key idea : - STACK: every method call gets its own small "frame" holding its parameters and
 *              local variables. When the method returns, the frame is thrown away.
 *              Like a stack of plates at a wedding: each call puts a plate on top, each return
 *              takes it off. Small, fast, and one stack per thread.
 *            - HEAP: every object made with 'new' lives here - one big shared storeroom.
 *              A local variable of an object type only holds a REFERENCE - an arrow, like the
 *              address of a house - pointing to the object on the heap.
 *            - The garbage collector cleans up heap objects that nothing points to any more.
 * Run      : java -cp out topic48_jvm_memory.StackVsHeap
 * Try this : Make depth() stop at 100 and see that no error happens.
 */
public class StackVsHeap {

    static class Box {
        int value;

        Box(int value) {
            this.value = value;
        }
    }

    static void change(int number, Box box) {
        number = 99;                 // changes only this frame's own copy
        box.value = 99;              // follows the arrow to the ONE real object on the heap - the caller sees this
        box = new Box(-1);           // only THIS frame's arrow now points somewhere else. The caller's arrow doesn't move
    }

    static int depth(int n) {
        return depth(n + 1);         // no base case: every call adds one more frame, forever
    }

    public static void main(String[] args) {
        int number = 1;              // a primitive: the value itself sits in main's stack frame
        Box box = new Box(1);        // 'box' (the arrow) is on the stack. The Box object is on the heap
        change(number, box);
        System.out.println("after change: number = " + number + ", box.value = " + box.value
                + "   (pass-by-value of a reference, topic 07)");

        Box first = new Box(5);
        Box second = first;          // two arrows, but only ONE object
        second.value = 6;
        System.out.println("first.value = " + first.value + " - both names point at the same heap object");
        first = null;
        second = null;               // now no arrow points to it, so the garbage collector is free to remove it

        // the stack is small and fixed for each thread. The heap is large and shared by all threads
        try {
            depth(0);
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError: too many frames (runaway recursion)");
        }

        Runtime runtime = Runtime.getRuntime();
        System.out.printf("heap: %d MB max, %d MB in use%n",
                runtime.maxMemory() / (1024 * 1024), (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));
        // OutOfMemoryError is the heap's version of this: too many live objects (not shown - it takes a while)
    }
}
