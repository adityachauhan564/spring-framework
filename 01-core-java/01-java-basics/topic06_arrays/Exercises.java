package topic06_arrays;

import java.util.Arrays;

/*
 * Exercises for topic 06.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic06_arrays.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Find the second-largest value, with ONE loop and without sorting: {70, 95, 40, 88} -> 88.
    //    You can assume there are at least two different values.
    static int secondLargest(int[] numbers) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Return a reversed COPY. The original array must stay the same.
    static int[] reversedCopy(int[] numbers) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Transpose a matrix - rows become columns: {{1,2,3},{4,5,6}} -> {{1,4},{2,5},{3,6}}.
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
