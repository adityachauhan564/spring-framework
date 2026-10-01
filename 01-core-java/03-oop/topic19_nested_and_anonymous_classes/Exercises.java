package topic19_nested_and_anonymous_classes;

/*
 * Exercises for topic 19.
 * How to use:
 *   - Complete the code below. Fill in every "TODO".
 *   - Then run:  java -cp out topic19_nested_and_anonymous_classes.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    interface Checker {
        boolean check(int value);
    }

    // 2. Return a Checker, written as an ANONYMOUS class, that says yes to values from min to max (both included).
    static Checker between(int min, int max) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    public static void main(String[] args) {
        // 1. A stack of ints, like a stack of plates at a wedding buffet:
        //    push puts a plate on top, pop takes the top plate off, peek just looks at the top plate
        LinkedStack stack = new LinkedStack();
        check(stack.isEmpty(), "exercise 1 a new stack is empty");
        stack.push(1);
        stack.push(2);
        stack.push(3);
        check(stack.peek() == 3 && stack.size() == 3, "exercise 1 push/peek/size");
        check(stack.pop() == 3 && stack.pop() == 2 && stack.size() == 1, "exercise 1 pop");

        Checker teen = between(13, 19);
        check(teen.check(13) && teen.check(19) && !teen.check(20), "exercise 2");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. Build the stack from "nodes": a private static nested class Node { int value; Node next; }
//    'top' points to the newest node. No code outside needs to know that Node even exists.
class LinkedStack {
    // TODO: the Node class and a 'top' field

    void push(int value) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    int pop() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    int peek() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    int size() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    boolean isEmpty() {
        throw new UnsupportedOperationException("TODO exercise 1");
    }
}
