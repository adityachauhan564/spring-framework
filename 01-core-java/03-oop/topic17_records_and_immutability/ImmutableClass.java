package topic17_records_and_immutability;

import java.util.Arrays;

/*
 * Topic    : Immutable classes and defensive copies
 * Key idea : an immutable object can't change after it's built: final class, private final
 *            fields, no setters. But 'final' only freezes the REFERENCE - if a field points at a
 *            mutable array, outsiders can still change its contents. So copy on the way in
 *            and on the way out ("defensive copies").
 * Run      : java -cp out topic17_records_and_immutability.ImmutableClass
 * Try this : remove the .clone() in the constructor and watch the "immutable" scores change.
 */
public class ImmutableClass {

    // final class: nobody can extend it and add mutable state
    static final class ExamResult {
        private final String student;
        private final int[] scores;

        ExamResult(String student, int[] scores) {
            this.student = student;
            this.scores = scores.clone();       // copy IN: the caller keeps their own array
        }

        String student() {
            return student;
        }

        int[] scores() {
            return scores.clone();              // copy OUT: callers can't reach our array
        }

        int total() {
            int sum = 0;
            for (int s : scores) {
                sum += s;
            }
            return sum;
        }
    }

    // a leaky version for comparison: final fields, yet NOT immutable
    static final class LeakyResult {
        private final int[] scores;

        LeakyResult(int[] scores) {
            this.scores = scores;               // shares the caller's array
        }

        int[] scores() {
            return scores;                      // hands out the internal array
        }
    }

    public static void main(String[] args) {
        int[] marks = {80, 90};

        ExamResult safe = new ExamResult("Asha", marks);
        LeakyResult leaky = new LeakyResult(marks);

        marks[0] = 0;                            // the caller changes THEIR array afterwards
        leaky.scores()[1] = 0;                   // and someone writes into the returned array

        System.out.println("safe:  " + Arrays.toString(safe.scores()) + " total " + safe.total());
        System.out.println("leaky: " + Arrays.toString(leaky.scores()) + "   <- changed from outside");

        // Strings and records of immutable values are immutable already; records don't copy arrays for you
        System.out.println("Immutable objects are safe to share between threads and to use as map keys.");
    }
}
