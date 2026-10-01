package topic27_queues_and_deques.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// Answers for topic27_queues_and_deques/Exercises.java
public class ExercisesSolution {

    static boolean isBalanced(String brackets) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : brackets.toCharArray()) {
            switch (c) {
                case '(', '[', '{' -> stack.push(c);          // an opening bracket: remember it
                default -> {
                    if (stack.isEmpty()) {
                        return false;                         // a closing bracket, but nothing was opened
                    }
                    char open = stack.pop();                  // the most recently opened bracket must close first
                    boolean matches = (open == '(' && c == ')') || (open == '[' && c == ']') || (open == '{' && c == '}');
                    if (!matches) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();                               // something still open at the end = not balanced
    }

    static List<Integer> smallest(int[] values, int k) {
        Queue<Integer> heap = new PriorityQueue<>();          // poll() always hands out the smallest
        for (int v : values) {
            heap.offer(v);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k && !heap.isEmpty(); i++) {
            result.add(heap.poll());
        }
        return result;
    }

    static List<String> printOrder(List<String> jobs) {
        Deque<String> queue = new ArrayDeque<>();
        for (String job : jobs) {
            if (job.startsWith("URGENT:")) {
                queue.offerFirst(job);                        // urgent: jump to the front of the line
            } else {
                queue.offerLast(job);                         // normal: join at the back
            }
        }
        List<String> done = new ArrayList<>();
        while (!queue.isEmpty()) {
            done.add(queue.pollFirst());                      // print from the front, one by one
        }
        return done;
    }

    public static void main(String[] args) {
        check(isBalanced("({[]})") && isBalanced(""), "exercise 1 balanced");
        check(!isBalanced("(]") && !isBalanced("((") && !isBalanced("())"), "exercise 1 not balanced");
        check(smallest(new int[] {5, 1, 4, 2}, 2).equals(List.of(1, 2)), "exercise 2");
        check(printOrder(List.of("a", "URGENT:b", "c")).equals(List.of("URGENT:b", "a", "c")), "exercise 3");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
