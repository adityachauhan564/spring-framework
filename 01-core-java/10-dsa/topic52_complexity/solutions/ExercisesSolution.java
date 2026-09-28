package topic52_complexity.solutions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Solutions for topic52_complexity/Exercises.java
public class ExercisesSolution {

    enum BigO { CONSTANT, LOG_N, N, N_LOG_N, N_SQUARED, TODO }

    //   a) one step                         b) one pass               c) a loop in a loop
    //   d) a good comparison sort           e) halving each step      f) i doubles: log2(n) steps
    static final List<BigO> ANSWERS = List.of(BigO.CONSTANT, BigO.N, BigO.N_SQUARED, BigO.N_LOG_N, BigO.LOG_N, BigO.LOG_N);

    static boolean hasDuplicate(int[] values) {
        Set<Integer> seen = new HashSet<>();            // O(1) add/contains, so O(n) overall
        for (int v : values) {
            if (!seen.add(v)) {
                return true;
            }
        }
        return false;
    }

    static int indexOf(int[] sorted, int target) {
        int low = 0, high = sorted.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (sorted[mid] == target) {
                return mid;
            } else if (sorted[mid] < target) {
                low = mid + 1;                          // the target can only be in the right half
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        check(ANSWERS.equals(List.of(BigO.CONSTANT, BigO.N, BigO.N_SQUARED, BigO.N_LOG_N, BigO.LOG_N, BigO.LOG_N)),
                "exercise 1");

        int[] big = new int[200_000];
        for (int i = 0; i < big.length; i++) big[i] = i;
        long start = System.nanoTime();
        check(!hasDuplicate(big), "exercise 2 no duplicate");
        big[199_999] = 5;
        check(hasDuplicate(big), "exercise 2 duplicate");
        check((System.nanoTime() - start) / 1_000_000 < 2000, "exercise 2 too slow - is it O(n^2)?");

        int[] sorted = {1, 3, 5, 7, 9, 11};
        check(indexOf(sorted, 7) == 3 && indexOf(sorted, 1) == 0 && indexOf(sorted, 4) == -1, "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
