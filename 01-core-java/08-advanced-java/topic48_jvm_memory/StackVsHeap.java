package topic48_jvm_memory;

/*
 * Topic    : Where data lives: the stack and the heap
 * Key idea : every method call gets a STACK FRAME holding its parameters and local variables;
 *            it disappears when the method returns. Objects (new ...) live on the HEAP; a local
 *            variable of an object type holds only a REFERENCE (an arrow) to it.
 *            The garbage collector frees heap objects that nothing refers to any more.
 * Run      : java -cp out topic48_jvm_memory.StackVsHeap
 * Try this : make depth() stop at 100 and see that no error happens.
 */
public class StackVsHeap {

    static class Box {
        int value;

        Box(int value) {
            this.value = value;
        }
    }

    static void change(int number, Box box) {
        number = 99;                 // changes this frame's copy only
        box.value = 99;              // follows the reference to the ONE object on the heap
        box = new Box(-1);           // this frame's reference now points elsewhere; the caller's doesn't
    }

    static int depth(int n) {
        return depth(n + 1);         // no base case: every call adds a stack frame
    }

    public static void main(String[] args) {
        int number = 1;              // a primitive: the value is in main's stack frame
        Box box = new Box(1);        // 'box' (the reference) is on the stack, the Box object on the heap
        change(number, box);
        System.out.println("after change: number = " + number + ", box.value = " + box.value
                + "   (pass-by-value of a reference, topic 07)");

        Box first = new Box(5);
        Box second = first;          // two references, ONE object
        second.value = 6;
        System.out.println("first.value = " + first.value + " - both names point at the same heap object");
        first = null;
        second = null;               // now nothing refers to it: the garbage collector may free it

        // the stack is small and fixed per thread; the heap is large and shared by all threads
        try {
            depth(0);
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError: too many frames (runaway recursion)");
        }

        Runtime runtime = Runtime.getRuntime();
        System.out.printf("heap: %d MB max, %d MB in use%n",
                runtime.maxMemory() / (1024 * 1024), (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));
        // OutOfMemoryError is the heap's version: too many live objects (not shown - it takes a while)
    }
}
