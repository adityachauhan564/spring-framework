package topic52_complexity;

import java.util.List;

/*
 * Exercises for topic 52. Replace each "TODO" with your answer, then run:
 *   java -cp out topic52_complexity.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    enum BigO { CONSTANT, LOG_N, N, N_LOG_N, N_SQUARED, TODO }

    // 1. Classify each snippet (n = the size of the input):
    //   a) return list.get(0);
    //   b) for (int x : list) total += x;
    //   c) for (int i ...) for (int j ...) compare(list[i], list[j]);
    //   d) Collections.sort(list);
    //   e) binary search in a sorted array
    //   f) for (int i = 1; i < n; i *= 2) count++;
    static final List<BigO> ANSWERS = List.of(BigO.TODO, BigO.TODO, BigO.TODO, BigO.TODO, BigO.TODO, BigO.TODO);

    // 2. true if any value appears twice - in O(n), so 200,000 values take milliseconds.
    //    (Two nested loops would be O(n^2): about 20 billion comparisons.)
    static boolean hasDuplicate(int[] values) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Binary search: the index of target in a SORTED array, or -1. It must be O(log n):
    //    halve the range on every step, no loop over every element.
    static int indexOf(int[] sorted, int target) {
        throw new UnsupportedOperationException("TODO exercise 3");
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
