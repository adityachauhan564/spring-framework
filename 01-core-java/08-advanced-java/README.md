# Module 08 · Advanced Java

**Difficulty:** Advanced · **Needs:** [03 OOP](../03-oop/), [06 Concurrency](../06-concurrency/) · **Next:** [09 Testing and build](../09-testing-and-build/)

## Why this module
These three topics turn "I can write Java" into "I understand the Java I read":
- the modern language features that current codebases and Spring use everywhere;
- what the JVM does with your objects in memory;
- the design patterns that frameworks are built from.

None of them needs a new library. Each needs the earlier modules to make sense.

| # | Topic | You'll be able to |
|---|---|---|
| 47 | [Modern Java features](./topic47_modern_java/) | read and write `var`, sealed types, pattern-matching switch, text blocks |
| 48 | [JVM memory and garbage collection](./topic48_jvm_memory/) | explain the stack and the heap, GC, and memory leaks |
| 49 | [Design patterns](./topic49_design_patterns/) | recognise Singleton, Factory, Builder, Strategy and Observer, in your code and in Spring |

## Compile and run
From this folder (`01-core-java/08-advanced-java`):
```bash
javac -d out $(find . -name "*.java")            # PowerShell: javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out topic48_jvm_memory.LeakDemo
java -cp out topic49_design_patterns.Exercises
```
Each topic works the same way as in [module 01](../01-java-basics/#how-to-work-through-a-topic).

## Module checklist
- [ ] I can explain what `sealed` guarantees and write an exhaustive pattern switch.
- [ ] I can say what makes an object eligible for GC, and name three ways Java programs leak memory.
- [ ] I can name the five patterns, the problem each solves, and where Spring uses it.
