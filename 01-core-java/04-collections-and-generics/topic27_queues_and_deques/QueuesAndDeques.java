package topic27_queues_and_deques;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Topic    : Queue, Deque (used as a stack) and PriorityQueue
 * Key idea : Queue          - FIFO: First In, First Out.
 *                             Like the line at a railway ticket counter - whoever came first is served first.
 *            Deque as stack - LIFO: Last In, First Out.
 *                             Like a pile of plates - you take the one you kept on top last.
 *            PriorityQueue  - always gives out the smallest (most important) item first,
 *                             no matter when it came in. Like a hospital emergency ward -
 *                             the most serious patient is seen first, not whoever came first.
 * Run      : java -cp out topic27_queues_and_deques.QueuesAndDeques
 * Try this : Use a Deque to check whether "({[]})" has balanced brackets.
 */
public class QueuesAndDeques {

    public static void main(String[] args) {
        // Queue (FIFO): offer() joins at the back of the line, poll() serves the person at the front
        Queue<String> line = new ArrayDeque<>();
        line.offer("first");
        line.offer("second");
        line.offer("third");
        System.out.println("peek:  " + line.peek() + "   (look, don't remove)");
        System.out.println("poll:  " + line.poll() + ", then " + line.poll() + ", left " + line);

        // Stack (LIFO): push() puts a plate on top, pop() takes the top plate.
        // Use ArrayDeque for this - the old Stack class is outdated
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("pop:   " + stack.pop() + ", then " + stack.pop() + ", left " + stack);

        // PriorityQueue: the smallest comes out first, whatever order they went in
        Queue<Integer> tasks = new PriorityQueue<>();
        tasks.offer(5);
        tasks.offer(1);
        tasks.offer(3);
        System.out.print("PriorityQueue order: ");
        while (!tasks.isEmpty()) {
            System.out.print(tasks.poll() + " ");
        }
        System.out.println();

        // poll() on an empty queue gently returns null. remove() would throw an exception instead
        line.clear();
        System.out.println("poll on empty: " + line.poll());
    }
}
