package topic19_collections_framework;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Topic    : Queue, Deque (stack) and PriorityQueue
 * Key idea : Queue         - FIFO: first in, first out (a line at a shop)
 *            Deque as stack - LIFO: last in, first out (a pile of plates)
 *            PriorityQueue - always hands out the smallest (or highest priority) first
 * Run      : java -cp out topic19_collections_framework.QueuesAndDeques
 * Try this : use a Deque to check if "({[]})" has balanced brackets.
 */
public class QueuesAndDeques {

    public static void main(String[] args) {
        // Queue (FIFO): offer adds at the back, poll removes from the front
        Queue<String> line = new ArrayDeque<>();
        line.offer("first");
        line.offer("second");
        line.offer("third");
        System.out.println("peek:  " + line.peek() + "   (look, don't remove)");
        System.out.println("poll:  " + line.poll() + ", then " + line.poll() + ", left " + line);

        // Stack (LIFO): use ArrayDeque, not the old Stack class
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("pop:   " + stack.pop() + ", then " + stack.pop() + ", left " + stack);

        // PriorityQueue: smallest first (a min-heap), no matter the insertion order
        Queue<Integer> tasks = new PriorityQueue<>();
        tasks.offer(5);
        tasks.offer(1);
        tasks.offer(3);
        System.out.print("PriorityQueue order: ");
        while (!tasks.isEmpty()) {
            System.out.print(tasks.poll() + " ");
        }
        System.out.println();

        // poll() on an empty queue returns null; remove() would throw
        line.clear();
        System.out.println("poll on empty: " + line.poll());
    }
}
