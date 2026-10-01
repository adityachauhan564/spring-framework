package topic09_static_and_final;

/*
 * Topic    : static vs instance members, and final
 * Key idea : Think of a classroom:
 *            - instance field = each student's own roll number. Every object has its own copy.
 *            - static field   = the class teacher's name. ONE copy, shared by every student.
 *              It belongs to the CLASS, not to any one object.
 *            - A static method also belongs to the class, so it has no 'this' (no "my object").
 *              That is why it cannot read instance fields.
 *            - final = the value is set once and can never change.
 * Run      : java -cp out topic09_static_and_final.StaticVsInstance
 * Try this : Make 'count' non-static and see what happens in totalCreated()
 *            (it won't compile - can you see why?).
 */
public class StaticVsInstance {

    static class Counter {
        static final int MAX = 3;          // a constant: static + final. By habit, constants are written in CAPITALS
        private static int count = 0;      // only ONE copy, shared by every Counter object

        private final int id;              // every object gets its OWN id, set once

        Counter() {
            count++;                       // the shared count goes up for every new object
            id = count;                    // this object remembers its own number
        }

        int getId() {                      // instance method: you need an object to call it (first.getId())
            return id;
        }

        static int totalCreated() {        // static method: you call it on the class (Counter.totalCreated())
            return count;
            // return id;   // compile error: there is no object here, so whose id?
        }
    }

    // static block: runs only once, the first time Java loads this class
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
