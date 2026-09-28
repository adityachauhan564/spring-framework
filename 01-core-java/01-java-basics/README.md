# Module 01 · Java basics

**Difficulty:** Beginner · **Needs:** nothing, this is the start · **Next:** [02 Objects and classes](../02-objects-and-classes/)

## Why this module
Everything later is built from these pieces: values and their types, expressions, decisions, loops, text, arrays and methods. By the end of this module you can write a small program that reads data from an array, decides, repeats and prints a result, and you can read a compiler error without panic.

| # | Topic | You'll be able to |
|---|---|---|
| 01 | [First program](./topic01_first_program/) | compile and run Java, and read compile and runtime errors |
| 02 | [Variables and data types](./topic02_variables_and_data_types/) | pick the right type, and convert safely |
| 03 | [Operators](./topic03_operators/) | predict what an expression evaluates to |
| 04 | [Control flow](./topic04_control_flow/) | choose between `if`, `switch` and the three loops |
| 05 | [Strings](./topic05_strings/) | work with text, and know why Strings never change |
| 06 | [Arrays](./topic06_arrays/) | store, loop over and copy many values |
| 07 | [Methods](./topic07_methods/) | split a program into reusable methods, and explain pass-by-value |

## How to work through a topic
1. Read the topic's README: first *why*, then *what you'll learn*.
2. Run each example, predict its output, then read the code. Every file starts with a short header: **Topic, Key idea, Run, Try this**.
3. Solve `Exercises.java`: fill in each `TODO` and run it until it prints "All exercises pass". Compare with `solutions/`.
4. Tick the revision checklist at the end of the README.

Exercises are small methods: until [topic 07](./topic07_methods/) explains methods properly, just fill in the method body. The `check(...)` lines in `main` test your answer.

## Compile and run
There's no build file. From this folder (`01-core-java/01-java-basics`), compile everything once, then run any class by its full name:
```bash
javac -d out $(find . -name "*.java")            # Git Bash, macOS, Linux
java -cp out topic04_control_flow.LoopTypes
java -cp out topic04_control_flow.Exercises       # your exercises
```
In PowerShell, compile with `javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName`.

Compile again after every change. `out/` is build output and isn't committed.

In an IDE (Eclipse, IntelliJ), make this folder the source folder, then run any file that has a `main` method.

## Module checklist
- [ ] I can compile and run a program from the terminal and fix a compile error from its message.
- [ ] I can explain `7 / 2`, `(int) 99.99` and `Integer == Integer`.
- [ ] I can write a loop that stops at the right moment, and choose `for`, `while` or `do-while`.
- [ ] I compare Strings with `equals` and know they're immutable.
- [ ] I can copy an array for real, and explain pass-by-value.
