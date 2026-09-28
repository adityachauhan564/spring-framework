package topic06_arrays;

import java.util.Arrays;

/*
 * Exercises for topic 06. Replace each "TODO" line with your code, then run:
 *   java -cp out topic06_arrays.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. The second-largest value, in ONE pass and without sorting: {70, 95, 40, 88} -> 88.
    //    Assume at least two different values.
    static int secondLargest(int[] numbers) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. A reversed COPY: the original array must not change.
    static int[] reversedCopy(int[] numbers) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Transpose a matrix: rows become columns. {{1,2,3},{4,5,6}} -> {{1,4},{2,5},{3,6}}.
    static int[][] transpose(int[][] matrix) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        check(secondLargest(new int[] {70, 95, 40, 88}) == 88, "exercise 1");
        check(secondLargest(new int[] {5, 1}) == 1, "exercise 1");

        int[] original = {1, 2, 3};
        check(Arrays.equals(reversedCopy(original), new int[] {3, 2, 1}), "exercise 2");
        check(Arrays.equals(original, new int[] {1, 2, 3}), "exercise 2 changed the original");

        int[][] expected = {{1, 4}, {2, 5}, {3, 6}};
        check(Arrays.deepEquals(transpose(new int[][] {{1, 2, 3}, {4, 5, 6}}), expected), "exercise 3");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
