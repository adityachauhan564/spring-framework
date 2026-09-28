package topic45_date_and_time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/*
 * Topic    : Dates and times with java.time (Java 8+)
 * Key idea : pick the type by what you actually know:
 *   LocalDate      a calendar date, no time, no zone        (a birthday)
 *   LocalTime      a time of day                            (the shop opens at 09:30)
 *   LocalDateTime  date + time, but no zone                 (a meeting on YOUR calendar)
 *   ZonedDateTime  date + time + time zone                  (a flight leaving Mumbai)
 *   Instant        a moment on the global timeline, in UTC  (a log timestamp)
 * All of them are immutable: plusDays() returns a NEW object.
 * Run      : java -cp out topic45_date_and_time.DateTimeDemo
 * Try this : how many days are left until your next birthday?
 */
public class DateTimeDemo {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2024, 2, 28);
        System.out.println("date:            " + date + ", a " + date.getDayOfWeek());
        System.out.println("plusDays(1):     " + date.plusDays(1) + "   (2024 is a leap year)");
        System.out.println("plusMonths(1):   " + date.plusMonths(1));
        System.out.println("original:        " + date + "   (unchanged: immutable)");

        LocalDateTime meeting = LocalDateTime.of(2024, 3, 15, 14, 30);
        System.out.println("meeting:         " + meeting + ", ends " + meeting.plusMinutes(45).toLocalTime());
        System.out.println("before 10:00?    " + LocalTime.of(9, 30).isBefore(LocalTime.of(10, 0)));

        // Period: in calendar units (years, months, days). Duration: in exact time (hours, seconds)
        Period age = Period.between(LocalDate.of(2000, 5, 20), LocalDate.of(2024, 3, 1));
        System.out.println("Period:          " + age.getYears() + " years " + age.getMonths() + " months " + age.getDays() + " days");
        Duration flight = Duration.between(LocalTime.of(6, 15), LocalTime.of(8, 40));
        System.out.println("Duration:        " + flight.toHours() + " h " + flight.toMinutesPart() + " min");
        System.out.println("days between:    " + ChronoUnit.DAYS.between(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)));

        // time zones: the SAME instant, shown in two zones
        ZonedDateTime mumbai = ZonedDateTime.of(2024, 3, 15, 9, 0, 0, 0, ZoneId.of("Asia/Kolkata"));
        ZonedDateTime london = mumbai.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println("09:00 in Mumbai: " + london.toLocalTime() + " in London");
        System.out.println("as an Instant:   " + mumbai.toInstant() + "   (UTC, the Z)");

        // formatting and parsing
        DateTimeFormatter indian = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("formatted:       " + date.format(indian));
        System.out.println("parsed:          " + LocalDate.parse("15/08/1947", indian));

        // questions you can ask a date
        System.out.println("weekend?         " + (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY));
        System.out.println("leap year?       " + date.isLeapYear());
        System.out.println("now (Instant):   " + Instant.now().isAfter(mumbai.toInstant()));
    }
}
