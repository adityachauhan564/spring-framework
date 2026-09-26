# IRCTC Ticket Booking (console app, work in progress)

> A plain-Java (no Spring) train-booking console app that stores users and trains in local JSON files through Jackson. This is the start of a build-along project.

## What it teaches
- Structuring a plain Java app into `entities/` and `services/` without a framework
- Using JSON files as a tiny "database": Jackson `ObjectMapper.readValue` with a `TypeReference<List<User>>`
- Modelling seats as a 2-D grid (`List<List<Integer>>`, where 0 = free) and a timetable as a `Map<station, time>`
- A Gradle build with a version catalog (`gradle/libs.versions.toml`) and a Java 21 toolchain

## Run it
Prerequisites: JDK 21. The Gradle 9.1 wrapper is included.
```bash
./gradlew test    # Windows: gradlew.bat test; checks users.json / trains.json are valid JSON arrays
./gradlew run     # runs org.example.App (currently does nothing, see Status)
```
The JSON "db" lives in `app/src/main/resources/localDb/`. `UserBookingService` reads it by a relative path (`src/main/resources/localDb/users.json`), so it must run with `app/` as the working directory. `gradle run` and `gradle test` both do that.

## Read the code in this order
1. `app/src/main/resources/localDb/users.json` and `trains.json`: the data shape.
2. `app/src/main/java/org/example/entities/`: `User`, `Ticket`, `Train`.
3. `app/src/main/java/org/example/services/UserBookingService.java`: loads the users list.
4. `app/src/test/java/org/example/AppTest.java`: guards the JSON files.

## Revision notes
- An anonymous subclass needs `new` and `()`: `new TypeReference<List<User>>(){}`. The generic type is captured by subclassing, which gets around type erasure.
- Jackson needs getters/setters (or field visibility) plus name mapping. The JSON uses `snake_case` (`user_id`, `ticket_booked`) while the fields are `camelCase`. Use `@JsonProperty` or `PropertyNamingStrategies.SNAKE_CASE`.
- `new File("relative/path")` resolves against the **working directory**, not the class. For read-only data, prefer classpath resources (`getResourceAsStream`).
- Store only a hash (e.g. BCrypt) of a password, never the plain text.

## Status
🚧 **Work in progress.**
- `App.main` and `TrainService` are empty.
- The entities have no getters/setters or JSON mapping, so `UserBookingService` can't deserialize `users.json` yet.
- `users.json` contains demo plain-text passwords (`password`, and a `hashedPassword` that isn't actually hashed). They are sample data only; never reuse them.
- Jackson 2.12.6 is old.

✅ `./gradlew test` passes.
