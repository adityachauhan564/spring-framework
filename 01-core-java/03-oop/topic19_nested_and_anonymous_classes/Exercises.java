package topic19_nested_and_anonymous_classes;

/*
 * Exercises for topic 19. Complete the code below, then run:
 *   java -cp out topic19_nested_and_anonymous_classes.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    interface Checker {
        boolean check(int value);
    }

    // 2. Return a Checker, written as an ANONYMOUS class, that accepts values between min and max inclusive.
    static Checker between(int min, int max) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    public static void main(String[] args) {
        // 1. A stack of ints: push adds on top, pop removes the top, peek looks at it
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 1. Build it from nodes: a private static nested class Node { int value; Node next; }
//    'top' points at the newest node. Nobody outside needs to know Node exists.
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
