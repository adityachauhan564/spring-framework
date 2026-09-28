package topic45_date_and_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/*
 * Exercises for topic 45. Replace each "TODO" line with your code, then run:
 *   java -cp out topic45_date_and_time.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. Age in whole years on a given day (Period.between).
    static int ageOn(LocalDate birthday, LocalDate today) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Days from today until the deadline; negative if it has passed (ChronoUnit.DAYS.between).
    static long daysUntil(LocalDate today, LocalDate deadline) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Is the shop open? Open 09:30 to 18:00 (18:00 itself is closed), Monday to Saturday.
    static boolean isOpen(LocalDateTime when) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    // 4. Format as "15 Aug 1947" (pattern "d MMM yyyy", English month names).
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
