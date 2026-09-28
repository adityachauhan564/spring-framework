# Topic 01 · Your first program

**Difficulty:** Beginner · **Needs:** nothing · **Next:** [02 Variables and data types](../topic02_variables_and_data_types/)

## Why it matters
Before learning any Java, you need the loop every Java developer runs thousands of times: write code, compile it, run it, read the error, fix it. Once that loop feels routine, every later topic is only new code to put through it.

## What you'll learn
- What `javac` and `java` each do, and what a `.class` file is
- Why every program has a `main` method, and what `public class` has to do with the file name
- The difference between an error from the compiler and an error while the program runs

## Run it
From the module folder (`01-java-basics`), compile once, then run any class:
```bash
javac -d out $(find . -name "*.java")
java -cp out topic01_first_program.HelloWorld
java -cp out topic01_first_program.HelloWorld one two      # arguments end up in args
java -cp out topic01_first_program.CompileVsRuntimeErrors
```

| File | Shows |
|---|---|
| `HelloWorld.java` | `main`, `println` vs `print`, comments, command-line arguments |
| `CompileVsRuntimeErrors.java` | typical compile errors and runtime errors, and how to read them |

## Key concepts
- **JDK:** the tools, including `javac`. **JVM:** runs compiled code.
- **Compiling:** `javac` compiles `.java` source into `.class` bytecode. `java` starts the JVM and runs a class's `main`.
- **Naming:** a `public class` must live in a file with the same name. The `package` line must match the folder.
- **Two kinds of error:** a compile-time error means nothing runs at all. A runtime error happens while running and prints a stack trace with the line number.

## Exercises
Each exercise is a small method. You only write its body, the part inside `{ }`; [topic 07](../topic07_methods/) explains methods properly. The `check(...)` lines in `main` test your answer.

Open `Exercises.java`, replace each `TODO`, and run `java -cp out topic01_first_program.Exercises`:
1. Build a name card string from two parameters.
2. Return the length of a text.

On paper, fix these three broken programs:
1. `System.out.println("Hi")`
2. `system.out.println("Hi");`
3. `public class Hello` saved in `Greeting.java`

Solutions are in `solutions/ExercisesSolution.java`.

## Common mistakes
- Running `java HelloWorld.class`. Give the class name, not the file: `java -cp out topic01_first_program.HelloWorld`.
- Leaving out the package when running: the full name is `topic01_first_program.HelloWorld`.
- Editing the code and running again without compiling again: the old `.class` runs.
- `System` spelled `system`: Java is case-sensitive.

## Related topics
- [02 Variables and data types](../topic02_variables_and_data_types/): what to put inside `main`
- [50 Unit testing](../../09-testing-and-build/): a better way to check code than reading printed output

## Revision checklist
- [ ] I can explain what `javac` produces and what `java` runs.
- [ ] I know why `public class HelloWorld` must be in `HelloWorld.java`.
- [ ] I can tell a compile error from a runtime error, and find the line number in each.
