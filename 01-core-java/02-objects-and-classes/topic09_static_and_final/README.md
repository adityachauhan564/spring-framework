# Topic 09 · static and final

**Difficulty:** Beginner · **Needs:** [08 Classes and objects](../topic08_classes_and_objects/) · **Next:** [10 Exception basics](../topic10_exception_basics/)

## Why it matters
Some data belongs to each object, like a ticket's own number. Other data belongs to the idea of a ticket: how many were issued, or a fixed price. Mixing the two up gives bugs where one object's change shows up in all the others, or code that won't compile ("non-static variable cannot be referenced from a static context"). `static` and `final` are the two keywords that make that difference explicit.

## What you'll learn
- Static fields (one copy for the class) vs instance fields (one per object)
- Static methods, and why they have no `this`
- Constants: `static final` with an `UPPER_CASE` name
- Static blocks, which run once when the class loads

## Run it
From `02-objects-and-classes` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic09_static_and_final.StaticVsInstance
```

## Key concepts
- **Static fields:** a `static` field is shared. Every object sees the same value, and changing it changes it for all.
- **Static methods:** a `static` method is called on the class (`Math.max`, `Counter.totalCreated()`). There's no object, so there's no `this` and no instance fields.
- **`final`:**
  - on a field: it's assigned exactly once;
  - on a local variable: it can't be reassigned;
  - on a class: it can't be extended.
- **Constants:** `static final int MAX = 3;`, one shared value that never changes.
- **`main` is static** because the JVM calls it before any object exists.

## Exercises
`Exercises.java` (run `java -cp out topic09_static_and_final.Exercises`):
1. A `Ticket` class that numbers each new ticket from a shared counter
2. A `MathUtils` utility class with a constant and static methods

## Common mistakes
- Using an instance field from `main` or another static method: a compile error.
- Making everything `static` to "fix" that error. Then there's only one of each value, and objects stop being separate.
- Treating a `static` field as a per-object value.

## Related topics
- [08 Classes and objects](../topic08_classes_and_objects/): instance state
- [49 Design patterns](../../08-advanced-java/topic49_design_patterns/): Singleton, a class with one shared instance

## Revision checklist
- [ ] I can explain why a static method can't use `this`.
- [ ] I can decide whether a field should be static or per object.
- [ ] I know what `final` means on a field, a local variable and a class.
