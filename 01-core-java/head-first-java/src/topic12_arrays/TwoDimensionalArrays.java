package topic12_arrays;

import java.util.Arrays;

/*
 * Topic    : 2D arrays (arrays of arrays)
 * Key idea : grid[row][col]. grid.length = number of rows, grid[r].length = columns in row r.
 * Run      : java -cp out topic12_arrays.TwoDimensionalArrays
 * Try this : print the matrix transposed (rows become columns).
 */
public class TwoDimensionalArrays {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Matrix:");
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }

        int diagonalSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            diagonalSum += matrix[i][i];
        }
        System.out.println("Diagonal sum: " + diagonalSum);
        System.out.println("deepToString: " + Arrays.deepToString(matrix));

        // rows can have different lengths ("jagged" array)
        int[][] jagged = new int[3][];
        jagged[0] = new int[] {1};
        jagged[1] = new int[] {1, 2};
        jagged[2] = new int[] {1, 2, 3};
        System.out.println("Jagged: " + Arrays.deepToString(jagged));
    }
}
