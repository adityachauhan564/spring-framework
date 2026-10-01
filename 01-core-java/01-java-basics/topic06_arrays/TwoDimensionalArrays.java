package topic06_arrays;

import java.util.Arrays;

/*
 * Topic    : 2D arrays (an array of arrays)
 * Key idea : Think of a cinema hall: rows and seats. grid[row][col] is one seat.
 *            - grid.length    = how many rows
 *            - grid[r].length = how many seats (columns) in row r
 * Run      : java -cp out topic06_arrays.TwoDimensionalArrays
 * Try this : Print the matrix transposed (rows become columns).
 */
public class TwoDimensionalArrays {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},      // row 0
                {4, 5, 6},      // row 1
                {7, 8, 9}       // row 2
        };

        // outer loop picks the row, inner loop walks through every column in that row
        System.out.println("Matrix:");
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }

        // diagonal = the boxes where row and column are the same: [0][0], [1][1], [2][2]
        int diagonalSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            diagonalSum += matrix[i][i];
        }
        System.out.println("Diagonal sum: " + diagonalSum);
        System.out.println("deepToString: " + Arrays.deepToString(matrix));   // deepToString prints a 2D array nicely

        // rows can have different lengths. This is called a "jagged" array
        int[][] jagged = new int[3][];          // 3 rows, the size of each row is decided later
        jagged[0] = new int[] {1};
        jagged[1] = new int[] {1, 2};
        jagged[2] = new int[] {1, 2, 3};
        System.out.println("Jagged: " + Arrays.deepToString(jagged));
    }
}
