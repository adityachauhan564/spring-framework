package topic06_arrays;

import java.util.Arrays;

/*
 * Topic    : Arrays
 * Key idea : - An array is a row of boxes of the same type, like seats in one train coach.
 *            - Its size is FIXED when you create it. You cannot add more seats later.
 *            - Positions (indexes) start from 0, so they go from 0 to length-1.
 *            - The java.util.Arrays class has ready-made helpers:
 *              toString, sort, fill, copyOf, binarySearch.
 * Run      : java -cp out topic06_arrays.ArraysDemo
 * Try this : Find the second-largest number in 'scores' using only one loop.
 */
public class ArraysDemo {

    public static void main(String[] args) {
        // two ways to create an array
        int[] scores = {70, 95, 40, 88, 62};     // size 5, values given right away
        String[] names = new String[3];          // size 3, every box starts empty (null)
        names[0] = "Asha";
        System.out.println("scores: " + Arrays.toString(scores));
        System.out.println("names:  " + Arrays.toString(names) + "  (defaults: 0, false, null)");

        // two ways to loop: with an index, or with for-each
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {   // .length has no () for arrays - it is not a method
            sum += scores[i];
        }
        int max = scores[0];
        for (int score : scores) {                  // for-each: "for every score in scores". Simple, but no index
            max = Math.max(max, score);
        }
        System.out.println("sum = " + sum + ", average = " + (double) sum / scores.length + ", max = " + max);

        // Arrays helper methods
        int[] copy = Arrays.copyOf(scores, scores.length);   // a separate copy - changing it won't touch scores
        Arrays.sort(copy);                                   // smallest to biggest
        System.out.println("sorted copy: " + Arrays.toString(copy) + ", original: " + Arrays.toString(scores));
        // binarySearch finds a value fast, but ONLY works on a sorted array
        System.out.println("binarySearch(88) in sorted copy -> index " + Arrays.binarySearch(copy, 88));

        int[] sevens = new int[4];
        Arrays.fill(sevens, 7);                              // put 7 in every box
        System.out.println("fill(7): " + Arrays.toString(sevens));

        // Careful: an array is an object. "alias = scores" does NOT copy the values.
        // Both names now point to the SAME array - like two people sharing one Google Doc link.
        int[] alias = scores;
        alias[0] = 0;
        System.out.println("after alias[0] = 0, scores[0] = " + scores[0]);

        // a.equals(b) only checks "same array object?". Arrays.equals(a, b) checks the values inside
        int[] a = {1, 2};
        int[] b = {1, 2};
        System.out.println("a.equals(b) = " + a.equals(b) + ", Arrays.equals(a, b) = " + Arrays.equals(a, b));

        // scores[5] would crash with ArrayIndexOutOfBoundsException (only 0 to 4 exist)
    }
}
