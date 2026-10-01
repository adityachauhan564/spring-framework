package topic17_records_and_immutability;

import java.util.Arrays;

/*
 * Topic    : Immutable classes and defensive copies
 * Key idea : An immutable object can NEVER change after it is created - like a printed
 *            Aadhaar card. Recipe: a final class, private final fields, and no setters.
 *            BUT there is a trap: 'final' only locks the ARROW (reference), not the box it points to.
 *            If a field points to an array, someone outside can still change what is inside the array.
 *            The fix: make a copy when the array comes IN, and give out a copy when it goes OUT.
 *            These are called "defensive copies".
 *            Like a bank giving you a photocopy of your passbook entries, never the original register.
 * Run      : java -cp out topic17_records_and_immutability.ImmutableClass
 * Try this : Remove the .clone() in the constructor and watch the "immutable" scores change.
 */
public class ImmutableClass {

    // final class: nobody can make a child class of it and add changeable data
    static final class ExamResult {
        private final String student;
        private final int[] scores;

        ExamResult(String student, int[] scores) {
            this.student = student;
            this.scores = scores.clone();       // copy IN: we keep our own copy, the caller keeps theirs
        }

        String student() {
            return student;
        }

        int[] scores() {
            return scores.clone();              // copy OUT: callers get a photocopy, never our real array
        }

        int total() {
            int sum = 0;
            for (int s : scores) {
                sum += s;
            }
            return sum;
        }
    }

    // a "leaky" version to compare: the fields are final, but the object is still NOT immutable
    static final class LeakyResult {
        private final int[] scores;

        LeakyResult(int[] scores) {
            this.scores = scores;               // keeps the caller's array itself - both point to the same box
        }

        int[] scores() {
            return scores;                      // hands out the real array - anyone can scribble on it
        }
    }

    public static void main(String[] args) {
        int[] marks = {80, 90};

        ExamResult safe = new ExamResult("Asha", marks);
        LeakyResult leaky = new LeakyResult(marks);

        marks[0] = 0;                            // the caller changes THEIR array later
        leaky.scores()[1] = 0;                   // and someone writes into the array they got back

        System.out.println("safe:  " + Arrays.toString(safe.scores()) + " total " + safe.total());
        System.out.println("leaky: " + Arrays.toString(leaky.scores()) + "   <- changed from outside");

        // Strings, and records made of immutable values, are already immutable.
        // But careful: a record does NOT copy arrays for you.
        System.out.println("Immutable objects are safe to share between threads and to use as map keys.");
    }
}
