# Topic 07 · Methods

**Difficulty:** Beginner · **Needs:** [06 Arrays](../topic06_arrays/) · **Next:** [08 Classes and objects](../../02-objects-and-classes/topic08_classes_and_objects/)

## Why it matters
A program written as one long `main` is hard to read, test and reuse. Methods let you name a piece of logic (`isPrime`, `area`), call it from many places, and test it on its own; every `Exercises.java` so far has been made of them. This topic also settles a question that confuses many Java developers: what happens to a variable you pass into a method.

## What you'll learn
- Parameters, return values and `void`
- Overloading: one name, different parameter lists
- Varargs (`int... numbers`)
- Recursion and its base case
- **Pass-by-value**, and what it means for primitives vs arrays and objects

## Run it
From `01-java-basics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic07_methods.MethodsDemo
```

## Key concepts
- **Signature:** a method's name plus its parameter types. That's what overloading changes; the return type alone can't.
- **Varargs:** `sum(int... numbers)` accepts zero or more ints, received as an array.
- **Recursion:** a method calling itself. It needs a **base case** that stops it, and each call must move towards it. Otherwise: `StackOverflowError`.
- **Pass-by-value:** Java always passes a **copy**.
  - For an `int`, that's a copy of the value, so the caller's variable can't change.
  - For an array or object, it's a copy of the **reference**. The method can change the object's contents, but reassigning the parameter doesn't affect the caller.

## Exercises
`Exercises.java` (run `java -cp out topic07_methods.Exercises`):
1. `isPrime`
2. A recursive `power`
3. Two overloaded `area` methods
4. `doubleAll`, which changes the caller's array
5. `max` with varargs

## Common mistakes
- Expecting a method to change the caller's `int` or reassign the caller's array.
- Recursion without a base case, or one the calls never reach.
- A non-`void` method where some path has no `return`: a compile error.
- Overloads that differ only in their return type: not allowed.

## Related topics
- [08 Classes and objects](../../02-objects-and-classes/topic08_classes_and_objects/): methods that belong to an object
- [09 static and final](../../02-objects-and-classes/topic09_static_and_final/): why these methods are `static`
- [48 JVM memory](../../08-advanced-java/topic48_jvm_memory/): the call stack that recursion uses

## Revision checklist
- [ ] I can explain pass-by-value using an array argument.
- [ ] I can name the base case of a recursive method and say what happens without one.
- [ ] I know what makes two overloads different.
