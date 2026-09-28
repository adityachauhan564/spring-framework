# Topic 03 · Operators

**Difficulty:** Beginner · **Needs:** [02 Variables and data types](../topic02_variables_and_data_types/) · **Next:** [04 Control flow](../topic04_control_flow/)

## Why it matters
Operators are how a program calculates and decides. A few of them behave differently from school maths: `7 / 2` is 3, `x++` gives the old value, and `&&` may skip its right side completely. Being able to predict an expression's result is what lets you write the conditions in the next topic correctly.

## What you'll learn
- Arithmetic, including integer division and the remainder `%`
- `++` / `--` before and after a variable, and compound assignment (`+=`)
- Comparison and logical operators, short-circuiting, and the ternary `? :`

## Run it
From `01-java-basics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic03_operators.Operators
```
Predict each printed line before you run it.

## Key concepts
- **Division:** `int / int` stays an `int`, and the remainder is dropped. Make one side a `double` (`2.0`) to keep decimals.
- **Remainder:** `n % 2 == 0` means even. `n % 10` is the last digit.
- **Increment:** `count++` uses the value and then adds 1. `++count` adds 1 first.
- **Short-circuit:** `&&` stops at the first `false`, and `||` stops at the first `true`. So `s != null && s.length() > 0` never throws.
- **Ternary:** `condition ? a : b` is an `if`/`else` that produces a value.
- **Order of evaluation:** `+` works left to right, so `1 + 2 + "3"` is `"33"` but `"1" + 2 + 3` is `"123"`.

## Exercises
`Exercises.java` (run `java -cp out topic03_operators.Exercises`):
1. Even or odd
2. Leap year in one boolean expression
3. The last digit of a number
4. An average that keeps its decimals
5. Adult or minor with the ternary operator

## Common mistakes
- Expecting `7 / 2 == 3.5`.
- Using `=` (assignment) where you meant `==` (comparison).
- Using `&` / `|` instead of `&&` / `||`: they always evaluate both sides.
- Using `++` inside a bigger expression, which makes it hard to read. Keep it on its own line.

## Related topics
- [04 Control flow](../topic04_control_flow/): the conditions you write with these operators
- [05 Strings](../topic05_strings/): `+` with text

## Revision checklist
- [ ] I can explain why `a != null && a.length() > 0` is safe and the `&` version isn't.
- [ ] I can give the result of `7 / 2`, `7 % 2` and `7 / 2.0`.
- [ ] I know the difference between `x++` and `++x`.
