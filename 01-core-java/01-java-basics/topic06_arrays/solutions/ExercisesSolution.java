package topic06_arrays.solutions;

import java.util.Arrays;

// Solutions for topic06_arrays/Exercises.java
public class ExercisesSolution {

    static int secondLargest(int[] numbers) {
        int largest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (int n : numbers) {
            if (n > largest) {
                second = largest;               // the old largest moves down one place
                largest = n;
            } else if (n > second && n != largest) {
                second = n;
            }
        }
        return second;
    }

    static int[] reversedCopy(int[] numbers) {
        int[] copy = new int[numbers.length];    // a new array: writing to it can't touch the original
        for (int i = 0; i < numbers.length; i++) {
            copy[i] = numbers[numbers.length - 1 - i];
        }
        return copy;
    }

    static int[][] transpose(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];   // rows and columns swap sizes
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
