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
 * Key idea : Pick the type based on what you actually know:
 *   LocalDate      only a date - no time, no zone               (your birthday)
 *   LocalTime      only a time of day                           (the shop opens at 09:30)
 *   LocalDateTime  date + time, but no time zone                (a meeting in YOUR calendar)
 *   ZonedDateTime  date + time + time zone                      (a flight leaving Mumbai at 9 AM IST)
 *   Instant        one exact moment for the whole world, in UTC (a timestamp in a log file)
 * All of them are immutable: plusDays() does NOT change the date, it gives you a NEW one.
 * Run      : java -cp out topic45_date_and_time.DateTimeDemo
 * Try this : How many days are left until your next birthday?
 */
public class DateTimeDemo {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2024, 2, 28);
        System.out.println("date:            " + date + ", a " + date.getDayOfWeek());
        System.out.println("plusDays(1):     " + date.plusDays(1) + "   (2024 is a leap year)");     // Java knows about 29 Feb
        System.out.println("plusMonths(1):   " + date.plusMonths(1));
        System.out.println("original:        " + date + "   (unchanged: immutable)");

        LocalDateTime meeting = LocalDateTime.of(2024, 3, 15, 14, 30);
        System.out.println("meeting:         " + meeting + ", ends " + meeting.plusMinutes(45).toLocalTime());
        System.out.println("before 10:00?    " + LocalTime.of(9, 30).isBefore(LocalTime.of(10, 0)));

        // Period = gap in calendar units (years, months, days) - good for age.
        // Duration = gap in exact clock time (hours, minutes, seconds) - good for a flight
        Period age = Period.between(LocalDate.of(2000, 5, 20), LocalDate.of(2024, 3, 1));
        System.out.println("Period:          " + age.getYears() + " years " + age.getMonths() + " months " + age.getDays() + " days");
        Duration flight = Duration.between(LocalTime.of(6, 15), LocalTime.of(8, 40));
        System.out.println("Duration:        " + flight.toHours() + " h " + flight.toMinutesPart() + " min");
        System.out.println("days between:    " + ChronoUnit.DAYS.between(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)));

        // time zones: the SAME moment, shown in two cities.
        // When it is 9 AM in Mumbai, what time is it for your client in London?
        ZonedDateTime mumbai = ZonedDateTime.of(2024, 3, 15, 9, 0, 0, 0, ZoneId.of("Asia/Kolkata"));
        ZonedDateTime london = mumbai.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println("09:00 in Mumbai: " + london.toLocalTime() + " in London");
        System.out.println("as an Instant:   " + mumbai.toInstant() + "   (UTC, the Z)");

        // formatting (date -> text) and parsing (text -> date), in the Indian dd/MM/yyyy style
        DateTimeFormatter indian = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("formatted:       " + date.format(indian));
        System.out.println("parsed:          " + LocalDate.parse("15/08/1947", indian));

        // questions you can ask a date
        System.out.println("weekend?         " + (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY));
        System.out.println("leap year?       " + date.isLeapYear());
        System.out.println("now (Instant):   " + Instant.now().isAfter(mumbai.toInstant()));
    }
}
