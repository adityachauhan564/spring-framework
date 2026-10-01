package topic06_arrays.solutions;

import java.util.Arrays;

// Answers for topic06_arrays/Exercises.java
public class ExercisesSolution {

    static int secondLargest(int[] numbers) {
        int largest = Integer.MIN_VALUE;        // start with the smallest possible int, so any real number beats it
        int second = Integer.MIN_VALUE;
        for (int n : numbers) {
            if (n > largest) {
                second = largest;               // new topper found: the old topper becomes second
                largest = n;
            } else if (n > second && n != largest) {
                second = n;                     // not the topper, but better than the current second
            }
        }
        return second;
    }

    static int[] reversedCopy(int[] numbers) {
        int[] copy = new int[numbers.length];    // a brand-new array, so writing to it can't change the original
        for (int i = 0; i < numbers.length; i++) {
            copy[i] = numbers[numbers.length - 1 - i];   // first box gets the last value, and so on
        }
        return copy;
    }

    static int[][] transpose(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];   // number of rows and columns swap
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                result[col][row] = matrix[row][col];
            }
        }
        return result;
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
