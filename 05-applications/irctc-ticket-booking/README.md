# IRCTC Ticket Booking (plain Java console app)

> A train-booking console app with **no Spring**: sign up and log in with hashed passwords, search trains by route, see a seat map, book and cancel seats. Users and trains are saved in two JSON files, through Jackson.

**Before this:** the other 05 projects. This one is the revision: the same ideas built by hand, so you can see what Spring was doing for you.

## Why it matters
With Spring it is easy to forget what the framework does. Without it:
- you create and connect the objects yourself (`App`'s constructor), where Spring would use dependency injection;
- you check passwords yourself (`BCrypt.checkpw`), where Spring Security would;
- you save data yourself (`JsonStore`), where Spring Data would;
- nothing makes two writes atomic (all or nothing), which `@Transactional` would.

Each of those is a small piece of code here, and the comments point out where Spring would take over.

## What it teaches
- Structuring an app without a framework: `entities/` (records), `services/` (rules), `store/` (files), `App` (the console)
- Jackson: JSON ↔ records, `snake_case` ↔ `camelCase`, `TypeReference<List<User>>`, `LocalDate` with the JSR-310 module
- Classpath resources vs files: read the default data shipped inside the jar, and write a copy into `./data`
- Safe file writes: write a temporary file, then move it into place
- Password hashing with BCrypt: a salt (random extra text), a slow hash, and `checkpw`
- A console menu with `Scanner` and a `switch` expression
- Gradle: the `application` plugin, a version catalog (`gradle/libs.versions.toml`), a Java toolchain, JUnit 5 with `@TempDir`

## Run it
Needs only JDK 21. The Gradle 9.1 wrapper is included.
```bash
./gradlew run -q --console=plain      # Windows: gradlew.bat run -q --console=plain
./gradlew test                        # 6 tests
```
Demo login: `aditya` / `password123`, or sign up (option 1).
- **Routes:** `bangalore → jaipur → delhi` (train `bacs`) and `delhi → kanpur → lucknow` (train `dlkn`).
- **Where the data goes:** bookings are saved in `app/data/` (`gradle run` starts in `app/`). Delete that folder to start again from the shipped data.

```
1 Sign up   2 Log in   3 Search trains   4 Book a seat
5 My bookings   6 Cancel a booking   0 Exit
Choose: 4
From: delhi
To: lucknow
Train id: dlkn
Seats (. free, X booked):
  row 1  . X . .
Row: 1
Seat: 2  ->  ! Seat 1-2 is taken or doesn't exist
```

## Read the code in this order
1. `app/src/main/resources/localDb/trains.json` and `users.json`: the shape of the data
2. `app/src/main/java/com/learning/irctc/entities/`: `Train` (with `runsBetween`, `isFree`), `Ticket`, `User`
3. `.../store/JsonStore.java`: reading and writing the files
4. `.../services/TrainService.java` and `UserBookingService.java`: the rules
5. `.../App.java`: the menu
6. `app/src/test/java/com/learning/irctc/AppTest.java`
7. `app/build.gradle` and `gradle/libs.versions.toml`

## Revision notes
- **Generic types:** `new TypeReference<List<User>>() {}` is an anonymous subclass. Java forgets generic types at runtime (type erasure), but a subclass keeps `List<User>` available. `List.class` alone would give a list of maps.
- **Naming:** the file uses `snake_case` (`train_no`), and Java uses `camelCase` (`trainNo`). One `PropertyNamingStrategies.SNAKE_CASE` on the `ObjectMapper` matches them all.
- **Records:** Jackson (2.12+) reads JSON into records through their constructor. The record is immutable (its fields can't be changed), but the lists inside it are not, and that is how a seat is marked booked.
- **Relative paths:** `new File("src/main/resources/...")` is looked up from the **working directory** (the folder you started the program in), so it breaks when started from anywhere else. Read shipped data with `getResourceAsStream` (the classpath). Write to a known folder, never into `src/`. And a jar can't be written to at all.
- **Passwords:** store only `BCrypt.hashpw(password, gensalt())`. Check with `BCrypt.checkpw`, which hashes again with the salt stored inside the hash. The course's `users.json` had plain-text passwords, and a "hashedPassword" that wasn't hashed.
- **Failed logins:** say "wrong name or password", not which one was wrong. Then no one can find out which names exist.
- **Store references, not copies:** a ticket stores the train id and the seat position. The course copied the whole train into every ticket, and that copy went out of date on the next booking.
- **No transactions:** a booking writes `trains.json` and then `users.json`. If the program dies in between, the seat is taken but no one has the ticket. A database transaction prevents exactly this, and that is why the other projects use one.
- **Known simplification:** seats belong to the train, not to a date. So booking seat 1-2 for one day also takes it for every other day. A real system like IRCTC keeps one seat chart per train per travel date.
- **JUnit 5 `@TempDir`:** a fresh folder for each test, deleted afterwards. So tests don't share data or touch `app/data`.

## Status
✅ **Working.** 6 tests pass, and the console flow was run from start to end (login, search, book, taken seat, my bookings).

Changes from the course version (which was an unfinished skeleton):
- the empty `App` and `TrainService` are implemented;
- the entities are records with JSON mapping;
- both data files are fixed: the same keys everywhere, no duplicate `user_id`, BCrypt hashes only;
- Jackson 2.12 → 2.19 and JUnit 4 → 5, and the unused Guava is removed;
- the package `org.example` is renamed to `com.learning.irctc`.
