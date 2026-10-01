package topic19_nested_and_anonymous_classes.solutions;

// Answers for topic19_nested_and_anonymous_classes/Exercises.java
public class ExercisesSolution {

    interface Checker {
        boolean check(int value);
    }

    static Checker between(int min, int max) {
        return new Checker() {                        // an anonymous class is allowed to use min and max
            @Override                                 // because they are never changed ("effectively final")
            public boolean check(int value) {
                return value >= min && value <= max;
            }
        };
    }

    public static void main(String[] args) {
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

class LinkedStack {

    // static: a Node doesn't need a link to its LinkedStack.
    // private: it is an inside detail that nobody else needs to see.
    private static class Node {
        final int value;
        final Node next;                              // the plate just below this one

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node top;
    private int size;

    void push(int value) {
        top = new Node(value, top);                   // the new plate sits on the old top plate
        size++;
    }

    int pop() {
        if (top == null) {
            throw new IllegalStateException("stack is empty");
        }
        int value = top.value;
        top = top.next;                               // the plate below now becomes the top
        size--;
        return value;
    }

    int peek() {
        if (top == null) {
            throw new IllegalStateException("stack is empty");
        }
        return top.value;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return top == null;
    }
}
