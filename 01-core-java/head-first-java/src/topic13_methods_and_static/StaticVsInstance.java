package topic13_methods_and_static;

/*
 * Topic    : static vs instance members, and final
 * Key idea : an instance field belongs to each object; a static field belongs to the
 *            CLASS and is shared by all objects. static methods have no 'this'.
 * Run      : java -cp out topic13_methods_and_static.StaticVsInstance
 * Try this : make 'count' non-static and see what totalCreated() does (it won't compile - why?).
 */
public class StaticVsInstance {

    static class Counter {
        static final int MAX = 3;          // constant: static + final, UPPER_CASE name
        private static int count = 0;      // one copy, shared by every Counter

        private final int id;              // one copy per object, set once

        Counter() {
            count++;
            id = count;
        }

        int getId() {                      // instance method: needs an object
            return id;
        }

        static int totalCreated() {        // static method: called on the class
            return count;
            // return id;   // compile error: which object's id?
        }
    }

    // static block: runs once, when the class is first loaded
    static {
        System.out.println("(static block ran - class loaded)");
    }

    public static void main(String[] args) {
        Counter first = new Counter();
        Counter second = new Counter();
        Counter third = new Counter();

        System.out.println("ids: " + first.getId() + ", " + second.getId() + ", " + third.getId());
        System.out.println("Counter.totalCreated() = " + Counter.totalCreated());
        System.out.println("Counter.MAX = " + Counter.MAX);
        System.out.println("Math.max(3, 7) = " + Math.max(3, 7) + "   <- a static method you already use");
    }
}
