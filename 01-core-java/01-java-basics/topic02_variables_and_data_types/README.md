# Topic 02 · Variables and data types

**Difficulty:** Beginner · **Needs:** [01 First program](../topic01_first_program/) · **Next:** [03 Operators](../topic03_operators/)

## Why it matters
Every value in a program has a type, and the type decides what the value can hold and what happens when you mix types. Most beginner bugs here are silent: a number that overflows into a negative, a decimal that gets cut off, two equal numbers that `==` says are different. Knowing the types turns those surprises into things you expect.

## What you'll learn
- The 8 primitive types and when to use `int`, `long`, `double`, `boolean` and `char`
- Widening (automatic) vs narrowing (a cast, and it can lose data)
- Wrapper classes (`Integer`, `Double`), autoboxing, and why wrappers are compared with `equals`

## Run it
From `01-java-basics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic02_variables_and_data_types.DataTypes
```

## Key concepts
- **Defaults:** whole numbers default to `int`, decimals to `double`. `long` needs `L` and `float` needs `f`.
- **Widening:** `int → long → double` is automatic and safe.
- **Narrowing:** `(int) 99.99` needs a cast and **cuts off** the decimals (99). `(byte) 130` wraps around to -126.
- **Wrappers:** `Integer` is an object. Collections need it, and it can be `null`. `Integer.parseInt("123")` turns text into a number.
- **Comparing wrappers:** `Integer` caches -128..127, so `==` on two `Integer` 1000s is `false`. Use `equals`.

## Exercises
`Exercises.java` (run `java -cp out topic02_variables_and_data_types.Exercises`):
1. Celsius → Fahrenheit
2. Multiply two ints without overflow
3. Compare two `Integer`s correctly
4. Drop the decimals of a price

## Common mistakes
- Thinking `(int) 99.99` rounds to 100. It gives 99; use `Math.round` to round.
- Writing `(long) (a * b)`: the multiplication overflows as an `int` first. Write `(long) a * b`.
- Comparing `Integer` objects with `==`.
- Unboxing a `null` `Integer` into an `int`, which throws `NullPointerException`.

## Related topics
- [03 Operators](../topic03_operators/): what happens when types meet in an expression
- [15 equals and hashCode](../../03-oop/topic15_equals_and_hashcode/): why `equals` and `==` differ for objects

## Revision checklist
- [ ] I can name the 8 primitives and pick one for money, a count, a yes/no, a letter.
- [ ] I can explain why `Integer x = 1000, y = 1000; x == y` is `false`.
- [ ] I know which conversions need a cast, and what a cast can lose.
