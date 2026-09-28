# Topic 04 · Control flow

**Difficulty:** Beginner · **Needs:** [03 Operators](../topic03_operators/) · **Next:** [05 Strings](../topic05_strings/)

## Why it matters
Without control flow a program runs every line exactly once, top to bottom. Decisions (`if`, `switch`) and repetition (loops) are what make a program react to its input. Almost every bug in this area is an off-by-one or a branch in the wrong order, and both are easy to spot once you know how each statement runs.

## What you'll learn
- `if` / `else if` / `else`, and why the order of the branches matters
- The old `switch` statement (with `break`) and the modern switch expression
- `for`, `while` and `do-while`, when to use each, and `break` / `continue`

## Run it
From `01-java-basics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic04_control_flow.GradeCalculator
java -cp out topic04_control_flow.LoopTypes
java -cp out topic04_control_flow.BottleSong      # a while loop plus if/else (Head First Java, chapter 1)
```

## Key concepts
- **else-if order:** the first condition that is true wins, so put the most specific check first.
- **Old `switch`:** without `break`, execution falls into the next case. Occasionally that's deliberate: two labels sharing one body.
- **Switch expression:** `case 'B', 'C' -> "passed";` returns a value, has no fall-through, and needs no `break`.
- **Choosing a loop:**
  - `for` when you know the count;
  - `while` to repeat until a condition changes;
  - `do-while` when the body must run at least once.
- **`break` and `continue`:** `break` leaves the loop; `continue` skips to the next pass.

## Exercises
`Exercises.java` (run `java -cp out topic04_control_flow.Exercises`):
1. FizzBuzz for one number
2. A multiplication table line
3. The sum of digits with a `while` loop
4. Days in a month with a switch expression

## Common mistakes
- Off-by-one errors: `i <= n` vs `i < n`. Count the passes on paper.
- A missing `break` in an old-style switch.
- A `while` loop whose variable never changes, so it runs forever.
- `if (x = 5)` instead of `if (x == 5)`, which is a compile error in Java.

## Related topics
- [03 Operators](../topic03_operators/): building the conditions
- [06 Arrays](../topic06_arrays/): looping over many values
- [16 Enums](../../03-oop/topic16_enums/): a switch over enum constants

## Revision checklist
- [ ] I can say when to use `while` rather than `for`, and when `do-while`.
- [ ] I can explain fall-through, and why the switch expression doesn't have it.
- [ ] I know why FizzBuzz checks "divisible by 15" before "by 3".
