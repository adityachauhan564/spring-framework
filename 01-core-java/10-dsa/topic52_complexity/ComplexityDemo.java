package topic52_complexity;

import java.util.Arrays;

/*
 * Topic    : Big-O - how much MORE work your code does when the input gets bigger
 * Key idea : Count the basic steps in terms of n (the input size). Keep only the part that
 *            grows fastest, and ignore constant numbers. When n doubles:
 *              O(1)     - no change at all             (opening the first page of a book)
 *              O(n)     - work doubles                 (reading every page once)
 *              O(n^2)   - work becomes about 4x        (comparing every student with every other student)
 *              O(log n) - just ONE more step           (finding a word in a dictionary by
 *                                                       opening it in the middle, again and again)
 * Run      : java -cp out topic52_complexity.ComplexityDemo
 * Try this : Add a column for n = 8000, and guess each number before you run it.
 */
public class ComplexityDemo {

    static long steps;                                   // counts the basic operations done

    // O(1): the same amount of work, whatever n is
    static int first(int[] a) {
        steps++;
        return a[0];
    }

    // O(n): one pass over the data
    static int sum(int[] a) {
        int total = 0;
        for (int x : a) {
            steps++;
            total += x;
        }
        return total;
    }

    // O(n^2): a loop inside a loop, over the same data
    static int countEqualPairs(int[] a) {
        int pairs = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                steps++;
                if (a[i] == a[j]) {
                    pairs++;
                }
            }
        }
        return pairs;
    }

    // O(log n): cut the remaining range in half on every step (binary search - only works on SORTED data)
    static int binarySearch(int[] sorted, int target) {
        int low = 0, high = sorted.length - 1;
        while (low <= high) {
            steps++;
            int mid = (low + high) >>> 1;                // >>> 1 divides by 2, and can't overflow like (low + high) / 2 can
            if (sorted[mid] == target) return mid;
            if (sorted[mid] < target) low = mid + 1;     // target is bigger: throw away the left half
            else high = mid - 1;                         // target is smaller: throw away the right half
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.printf("%-8s %10s %10s %12s %10s%n", "n", "O(1)", "O(n)", "O(n^2)", "O(log n)");
        for (int n : new int[] {1000, 2000, 4000}) {
            int[] data = new int[n];
            Arrays.setAll(data, i -> i);                 // fills 0, 1, 2, ... - already sorted

            // run each method and record how many steps it took
            long[] counts = new long[4];
            steps = 0; first(data);                 counts[0] = steps;
            steps = 0; sum(data);                   counts[1] = steps;
            steps = 0; countEqualPairs(data);       counts[2] = steps;
            steps = 0; binarySearch(data, n - 1);   counts[3] = steps;
            System.out.printf("%-8d %10d %10d %12d %10d%n", n, counts[0], counts[1], counts[2], counts[3]);
        }
        System.out.println("n doubles: O(n) doubles, O(n^2) roughly x4, O(log n) +1");
        // O(n log n) - like Arrays.sort - sits between O(n) and O(n^2). That is the cost of a good sort.
    }
}
