# Topic 10 · Exception basics

**Difficulty:** Beginner · **Needs:** [08 Classes and objects](../topic08_classes_and_objects/) · **Next:** [11 Encapsulation](../../03-oop/topic11_encapsulation_and_access_modifiers/)

## Why it matters
Things go wrong: a file is missing, the user types "abc" where you wanted a number, a divisor is zero. Without exception handling, the program crashes with a stack trace. With it, you decide what happens instead. Just as important is the opposite: rejecting bad input yourself with `throw`, so a bug shows up where it starts rather than three methods later.

## What you'll learn
- `try` / `catch` / `finally`, and the order of `catch` blocks
- Checked exceptions (the compiler forces you to handle them) vs unchecked ones
- `throw` to reject bad input, and `throws` to declare a checked exception
- try-with-resources, which closes files and connections automatically

## Run it
From `02-objects-and-classes` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic10_exception_basics.CheckedExceptionDemo    # create myFile.txt and run it again
java -cp out topic10_exception_basics.UncheckedExceptionDemo
java -cp out topic10_exception_basics.ThrowAndFinally
```

## Key concepts
- **Checked vs unchecked:**
  - checked (`IOException`, anything extending `Exception`): you must catch it or declare `throws`;
  - unchecked (`RuntimeException` and its subclasses, like `NullPointerException` or `IllegalArgumentException`): the compiler doesn't insist.
- **Catch order:** put the most specific `catch` first: `FileNotFoundException` before `IOException`.
- **`finally`:** always runs, whether the `try` succeeds or throws.
- **try-with-resources:** `try (var reader = ...)` closes the resource for you, even on an error.
- **Prevent or throw:** prevent a bug with a check (`if (text == null)`) rather than catching `NullPointerException`. Throw `IllegalArgumentException` for bad arguments.

## Exercises
`Exercises.java` (run `java -cp out topic10_exception_basics.Exercises`):
1. Parse a number, with a fallback
2. A divide method that throws for zero
3. Count the valid numbers in a list, handling each item on its own

## Common mistakes
- An empty `catch {}` that hides the error completely.
- `catch (Exception e)` everywhere: it also catches bugs you wanted to see.
- Not closing files or connections: use try-with-resources.
- Catching `NullPointerException` instead of fixing the null.

## Related topics
- [18 Custom exceptions](../../03-oop/topic18_custom_exceptions/): your own exception types
- [44 File I/O](../../07-java-apis/topic44_file_io/): the checked exceptions you'll meet most

## Revision checklist
- [ ] I can say when `throws` is required.
- [ ] I can explain checked vs unchecked with one example of each.
- [ ] I know what `finally` and try-with-resources guarantee.
