# Topic 45 · Date and time

**Difficulty:** Intermediate · **Needs:** [08 Classes and objects](../../02-objects-and-classes/topic08_classes_and_objects/) · **Next:** [46 Networking with sockets](../topic46_sockets/)

## Why it matters
Dates look simple until they aren't:
- months have different lengths, and leap years exist;
- "09:00" means different moments in Mumbai and London;
- a user's age changes on their birthday, not on 1 January.

The old `java.util.Date` got most of this wrong, and it could be changed by anyone holding it. The `java.time` API (Java 8+) has a clear, immutable type for each situation, which is what the booking and scheduling apps in [stage 05](../../../05-applications/) rely on.

## What you'll learn
- `LocalDate`, `LocalTime`, `LocalDateTime`, `ZonedDateTime`, `Instant`, and when to use each
- `plusDays` / `minusMonths`, comparing with `isBefore` / `isAfter`
- `Period` (calendar units) vs `Duration` (exact time), and `ChronoUnit.DAYS.between`
- Time zones: `withZoneSameInstant`
- Formatting and parsing with `DateTimeFormatter`

## Run it
From `07-java-apis` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic45_date_and_time.DateTimeDemo
```

## Key concepts
- **Choose the type by what you know.** A birthday has no time or zone, so it's a `LocalDate`. A flight departure needs a zone, so it's a `ZonedDateTime`. A log timestamp is a moment, so it's an `Instant`.
- **Immutability:** `date.plusDays(1)` returns a **new** date, and the original never changes, like `String`.
- **`Period` vs `Duration`:** `Period` counts years, months and days. `Duration` counts hours, minutes and seconds.
- **Formatting:** `DateTimeFormatter.ofPattern("dd/MM/yyyy")` for both `format` and `parse`. Pass a `Locale` when month or day names appear.

## Exercises
`Exercises.java` (run `java -cp out topic45_date_and_time.Exercises`):
1. Age on a given day
2. Days until a deadline
3. Is the shop open?
4. Format a date with month names

## Common mistakes
- Using the old `Date` / `Calendar` classes in new code.
- Calling `date.plusDays(1);` without using the result.
- Storing a moment as a `LocalDateTime` and losing the time zone.
- Computing an age as `today.getYear() - birth.getYear()`, which is wrong before the birthday.
- Mixing up `mm` (minutes) and `MM` (months) in a pattern.

## Related topics
- [17 Records and immutability](../../03-oop/topic17_records_and_immutability/): why immutable values are safe
- [05 Applications, showtime](../../../05-applications/showtime/): show times as `LocalDateTime`

## Revision checklist
- [ ] I can pick the right `java.time` type for a birthday, a meeting and a log entry.
- [ ] I can explain Period vs Duration.
- [ ] I know that every `java.time` object is immutable.
