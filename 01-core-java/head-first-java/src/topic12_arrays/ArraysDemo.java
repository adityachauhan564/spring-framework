package topic12_arrays;

import java.util.Arrays;

/*
 * Topic    : Arrays
 * Key idea : an array has a FIXED size set at creation; indexes go 0 .. length-1.
 *            java.util.Arrays has the helpers (toString, sort, fill, copyOf, binarySearch).
 * Run      : java -cp out topic12_arrays.ArraysDemo
 * Try this : find the second-largest number in 'scores' with one loop.
 */
public class ArraysDemo {

    public static void main(String[] args) {
        // create
        int[] scores = {70, 95, 40, 88, 62};     // size 5, known values
        String[] names = new String[3];          // size 3, filled with defaults (null)
        names[0] = "Asha";
        System.out.println("scores: " + Arrays.toString(scores));
        System.out.println("names:  " + Arrays.toString(names) + "  (defaults: 0, false, null)");

        // loop with index vs for-each
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {   // .length is a field, no ()
            sum += scores[i];
        }
        int max = scores[0];
        for (int score : scores) {
            max = Math.max(max, score);
        }
        System.out.println("sum = " + sum + ", average = " + (double) sum / scores.length + ", max = " + max);

        // Arrays helpers
        int[] copy = Arrays.copyOf(scores, scores.length);   // independent copy
        Arrays.sort(copy);
        System.out.println("sorted copy: " + Arrays.toString(copy) + ", original: " + Arrays.toString(scores));
        System.out.println("binarySearch(88) in sorted copy -> index " + Arrays.binarySearch(copy, 88));

        int[] sevens = new int[4];
        Arrays.fill(sevens, 7);
        System.out.println("fill(7): " + Arrays.toString(sevens));

        // arrays are objects: assigning copies the REFERENCE, not the values
        int[] alias = scores;
        alias[0] = 0;
        System.out.println("after alias[0] = 0, scores[0] = " + scores[0]);

        // equals() compares references; Arrays.equals() compares contents
        int[] a = {1, 2};
        int[] b = {1, 2};
        System.out.println("a.equals(b) = " + a.equals(b) + ", Arrays.equals(a, b) = " + Arrays.equals(a, b));

        // scores[5] would throw ArrayIndexOutOfBoundsException (valid indexes: 0..4)
    }
}
