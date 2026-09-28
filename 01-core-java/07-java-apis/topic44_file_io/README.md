# Topic 44 · File I/O

**Difficulty:** Intermediate · **Needs:** [10 Exception basics](../../02-objects-and-classes/topic10_exception_basics/) · **Next:** [45 Date and time](../topic45_date_and_time/)

## Why it matters
Programs read configuration, import CSV files, write logs and export reports. The modern `java.nio.file` API (`Path` + `Files`) makes the common cases one line each. The two things that matter are choosing between "read it all" and "read it line by line", and always closing what you open, because an unclosed file keeps a lock and a handle until the program ends.

## What you'll learn
- `Path` and the `Files` helpers: `writeString`, `readAllLines`, `write`, `exists`, `size`, `createTempFile`
- Appending with `StandardOpenOption.APPEND` (plus `CREATE`)
- `Files.newBufferedReader` / `newBufferedWriter` for any size of file
- try-with-resources, and `IOException`

## Run it
From `07-java-apis` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic44_file_io.FileIODemo
```
It works in a temporary file and deletes it at the end.

## Key concepts
- **`Path`** names a file; the static `Files` methods do the work. `Path.of("data", "notes.txt")` builds a path that works on every OS.
- **Small files:** `readAllLines` / `readString` read everything into memory.
- **Big files:** read line by line with a `BufferedReader` until `readLine()` returns `null`.
- **Writing:** `writeString` / `write` create or **replace** a file. Add `APPEND` to add to the end, and `CREATE` too if it might not exist.
- **Closing:** readers and writers must be closed. try-with-resources does it, even on an exception.
- **Relative paths:** they're resolved from the folder you **run** `java` from, not the source folder.

## Exercises
`Exercises.java` (run `java -cp out topic44_file_io.Exercises`):
1. Count matching lines, reading line by line
2. Save settings as sorted `key=value` lines
3. Append to a log that may not exist yet

## Common mistakes
- `readAllLines` on a huge file (an `OutOfMemoryError`).
- Forgetting `APPEND`, which overwrites the file.
- Opening a reader without try-with-resources.
- Relative paths that work in the IDE but not from the terminal.

## Related topics
- [10 Exception basics](../../02-objects-and-classes/topic10_exception_basics/): checked `IOException`, try-with-resources
- [46 Sockets](../topic46_sockets/): the same stream ideas over a network

## Revision checklist
- [ ] I know when to use `readAllLines` and when to read line by line.
- [ ] I can append to a file without replacing it.
- [ ] I always open readers and writers in try-with-resources.
