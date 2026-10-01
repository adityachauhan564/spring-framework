package topic45_date_and_time.solutions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

// Answers for topic45_date_and_time/Exercises.java
public class ExercisesSolution {

    static int ageOn(LocalDate birthday, LocalDate today) {
        return Period.between(birthday, today).getYears();       // understands the calendar: months, leap years, all of it
    }

    static long daysUntil(LocalDate today, LocalDate deadline) {
        return ChronoUnit.DAYS.between(today, deadline);         // goes negative by itself when the deadline is in the past
    }

    static boolean isOpen(LocalDateTime when) {
        LocalTime time = when.toLocalTime();
        boolean workingDay = when.getDayOfWeek() != DayOfWeek.SUNDAY;
        // "not before 09:30" means 09:30 or later. "before 18:00" means 18:00 itself is closed
        return workingDay && !time.isBefore(LocalTime.of(9, 30)) && time.isBefore(LocalTime.of(18, 0));
    }

    static String pretty(LocalDate date) {
        // Locale.ENGLISH fixes the month names. Without it, a computer set to Hindi would print Hindi month names
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH));
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
