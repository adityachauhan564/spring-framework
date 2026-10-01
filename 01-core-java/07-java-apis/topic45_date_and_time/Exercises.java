package topic45_date_and_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/*
 * Exercises for topic 45.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic45_date_and_time.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Age in full years on a given day. (Use Period.between.)
    static int ageOn(LocalDate birthday, LocalDate today) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Days from today until the deadline. Negative if the deadline has already passed.
    //    (Use ChronoUnit.DAYS.between.)
    static long daysUntil(LocalDate today, LocalDate deadline) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Is the shop open? It is open from 09:30 to 18:00 (at exactly 18:00 it is already closed),
    //    Monday to Saturday. Sunday is a holiday.
    static boolean isOpen(LocalDateTime when) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Format a date like "15 Aug 1947" (pattern "d MMM yyyy", with English month names).
    static String pretty(LocalDate date) {
        throw new UnsupportedOperationException("TODO exercise 4");
    }

    public static void main(String[] args) {
        check(ageOn(LocalDate.of(2000, 5, 20), LocalDate.of(2024, 5, 19)) == 23, "exercise 1 day before the birthday");
        check(ageOn(LocalDate.of(2000, 5, 20), LocalDate.of(2024, 5, 20)) == 24, "exercise 1 on the birthday");
        check(daysUntil(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 1)) == 60, "exercise 2 (leap year)");
        check(daysUntil(LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 1)) == -9, "exercise 2 past deadline");
        check(isOpen(LocalDateTime.of(2024, 3, 15, 10, 0)), "exercise 3 Friday 10:00");
        check(!isOpen(LocalDateTime.of(2024, 3, 15, 18, 0)), "exercise 3 Friday 18:00");
        check(!isOpen(LocalDateTime.of(2024, 3, 17, 12, 0)), "exercise 3 Sunday");
        check(!isOpen(LocalDate.of(2024, 3, 16).atTime(LocalTime.of(9, 0))), "exercise 3 Saturday 09:00");
        check(pretty(LocalDate.of(1947, 8, 15)).equals("15 Aug 1947"), "exercise 4");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
