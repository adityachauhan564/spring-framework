# Topic 05 · Strings

**Difficulty:** Beginner · **Needs:** [04 Control flow](../topic04_control_flow/) · **Next:** [06 Arrays](../topic06_arrays/)

## Why it matters
Almost every program handles text: names, messages, input, file contents, JSON. Java's `String` has one rule that surprises everyone once: it can **never change**. Every "change" makes a new string. That rule explains most string bugs, and it's why `StringBuilder` exists.

## What you'll learn
- The common `String` methods: `length`, `charAt`, `substring`, `indexOf`, `split`, `replace`, `trim`
- Immutability, and why `s.toUpperCase();` on its own does nothing
- `==` vs `equals`, and the String pool
- `StringBuilder` for building text in a loop, and `String.format`

## Run it
From `01-java-basics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic05_strings.StringBasics
java -cp out topic05_strings.StringBuilderDemo
```

## Key concepts
- **Indexes:** they start at 0. `substring(start, end)` includes `start` and excludes `end`.
- **Immutability:** a method like `toUpperCase()` *returns* a new String. Keep it with `s = s.toUpperCase();`.
- **Comparing:** `==` asks "same object?" and `equals` asks "same text?". Literals are shared in the String pool, which is why `==` sometimes seems to work. Don't rely on it.
- **Null safety:** `"yes".equals(input)` is safe even when `input` is `null`.
- **`StringBuilder`:** a changeable buffer. Using `append` in a loop avoids making a new String on every pass.

## Exercises
`Exercises.java` (run `java -cp out topic05_strings.Exercises`):
1. Palindrome check (ignoring case)
2. Count the vowels
3. Capitalise every word with a `StringBuilder`
4. Mask a card number

## Common mistakes
- Comparing text with `==`.
- Calling `s.trim();` and expecting `s` to change.
- Building a long string with `+` inside a loop.
- `substring(1, 3)` returning 2 characters, not 3: the end is excluded.

## Related topics
- [02 Variables and data types](../topic02_variables_and_data_types/): `char` vs `String`
- [15 equals and hashCode](../../03-oop/topic15_equals_and_hashcode/): what `equals` means for your own classes
- [17 Records and immutability](../../03-oop/topic17_records_and_immutability/): immutability for your own classes

## Revision checklist
- [ ] I can explain why `s.toUpperCase();` on its own changes nothing.
- [ ] I can explain why `"java" == new String("java")` is `false`.
- [ ] I know when to use `StringBuilder` instead of `+`.
