package topic52_complexity;

import java.util.Arrays;

/*
 * Topic    : Big-O - how the work grows when the input grows
 * Key idea : count the basic steps as a function of n, keep only the fastest-growing part,
 *            and drop constants. Doubling n doubles O(n) work, quadruples O(n^2) work,
 *            and adds ONE step to O(log n) work.
 * Run      : java -cp out topic52_complexity.ComplexityDemo
 * Try this : add a column for n = 8000 and predict each number first.
 */
public class ComplexityDemo {

    static long steps;                                   // counts the basic operations

    // O(1): the same work whatever n is
    static int first(int[] a) {
        steps++;
        return a[0];
    }

    // O(n): one pass
    static int sum(int[] a) {
        int total = 0;
        for (int x : a) {
            steps++;
            total += x;
        }
        return total;
    }

    // O(n^2): a loop inside a loop over the same data
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

    // O(log n): halve the remaining range each step (binary search on SORTED data)
    static int binarySearch(int[] sorted, int target) {
        int low = 0, high = sorted.length - 1;
        while (low <= high) {
            steps++;
            int mid = (low + high) >>> 1;                // >>> 1 halves without overflowing
            if (sorted[mid] == target) return mid;
            if (sorted[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.printf("%-8s %10s %10s %12s %10s%n", "n", "O(1)", "O(n)", "O(n^2)", "O(log n)");
        for (int n : new int[] {1000, 2000, 4000}) {
            int[] data = new int[n];
            Arrays.setAll(data, i -> i);                 // 0, 1, 2, ... already sorted

            long[] counts = new long[4];
            steps = 0; first(data);                 counts[0] = steps;
            steps = 0; sum(data);                   counts[1] = steps;
            steps = 0; countEqualPairs(data);       counts[2] = steps;
            steps = 0; binarySearch(data, n - 1);   counts[3] = steps;
            System.out.printf("%-8d %10d %10d %12d %10d%n", n, counts[0], counts[1], counts[2], counts[3]);
        }
        System.out.println("n doubles: O(n) doubles, O(n^2) roughly x4, O(log n) +1");
        // O(n log n) - e.g. Arrays.sort - sits between O(n) and O(n^2): the cost of a good sort.
    }
}
