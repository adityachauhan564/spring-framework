# Module 07 · Java APIs

**Difficulty:** Intermediate · **Needs:** [02 Objects and classes](../02-objects-and-classes/) (topic 46 also needs [06 Concurrency](../06-concurrency/)) · **Next:** [08 Advanced Java](../08-advanced-java/)

## Why this module
Programs talk to the outside world. They read and write files, handle dates and times, and send data over the network. These three JDK APIs are ones every backend developer uses, directly or through a framework. Each has one classic trap: an unclosed file, a lost time zone, an unflushed socket. Knowing those traps saves hours of debugging later.

| # | Topic | You'll be able to |
|---|---|---|
| 44 | [File I/O](./topic44_file_io/) | read and write files safely, of any size |
| 45 | [Date and time](./topic45_date_and_time/) | pick the right `java.time` type and calculate with dates |
| 46 | [Networking with sockets](./topic46_sockets/) | see how a server and a client talk, one thread per client |

## Compile and run
From this folder (`01-core-java/07-java-apis`):
```bash
javac -d out $(find . -name "*.java")            # PowerShell: javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out topic46_sockets.SocketDemo
java -cp out topic45_date_and_time.Exercises
```
Each topic works the same way as in [module 01](../01-java-basics/#how-to-work-through-a-topic).

## Module checklist
- [ ] I choose between reading a whole file and reading line by line, and close every reader.
- [ ] I pick `LocalDate` / `LocalDateTime` / `ZonedDateTime` / `Instant` deliberately.
- [ ] I can explain Period vs Duration.
- [ ] I can explain `accept()`, flushing, and a thread per client.
