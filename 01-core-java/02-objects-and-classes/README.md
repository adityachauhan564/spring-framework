# Module 02 · Objects and classes

**Difficulty:** Beginner · **Needs:** [01 Java basics](../01-java-basics/) · **Next:** [03 OOP](../03-oop/)

## Why this module
Module 01 put everything in `static` methods and local variables. Real programs are made of objects, each keeping its own data and knowing what it can do. This module is the switch from "a list of instructions" to "things that work together". It also covers what to do when something goes wrong, because from here on your methods will reject bad input.

| # | Topic | You'll be able to |
|---|---|---|
| 08 | [Classes and objects](./topic08_classes_and_objects/) | design a class and create objects that keep their own state |
| 09 | [static and final](./topic09_static_and_final/) | tell class-level members from object-level ones |
| 10 | [Exception basics](./topic10_exception_basics/) | handle failures, and reject bad input with `throw` |

## Compile and run
From this folder (`01-core-java/02-objects-and-classes`):
```bash
javac -d out $(find . -name "*.java")            # PowerShell: javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out topic08_classes_and_objects.GameLauncher
java -cp out topic08_classes_and_objects.Exercises
```
Each topic works the same way as in [module 01](../01-java-basics/#how-to-work-through-a-topic): README, examples, `Exercises.java`, `solutions/`, checklist.

## Module checklist
- [ ] I can write a class with private final fields, a constructor, getters and `toString`.
- [ ] I can decide whether a field should be `static` or belong to each object.
- [ ] I can handle a checked exception, and throw an `IllegalArgumentException` for bad input.
