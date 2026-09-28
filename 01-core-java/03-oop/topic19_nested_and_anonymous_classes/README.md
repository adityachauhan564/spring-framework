# Topic 19 · Nested and anonymous classes

**Difficulty:** Intermediate · **Needs:** [14 Interfaces](../topic14_interfaces_and_dependency_injection/) · **Next:** [20 Lambdas and functional interfaces](../topic20_lambdas_and_functional_interfaces/)

## Why it matters
Library code is full of classes inside classes: `Map.Entry`, a list's private `Node`, builders, event listeners written inline. Knowing the four kinds lets you read that code. It also lets you hide helper classes nobody else should use. The anonymous class is the direct ancestor of the lambda: once you've written one, the next topic's shortcut makes sense.

## What you'll learn
- **Static nested classes**, the usual choice, like a private `Node`
- **Inner classes**, which are tied to an outer object and can use its fields
- **Local and anonymous classes**, one-off implementations written where they're used

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic19_nested_and_anonymous_classes.NestedClasses
```

## Key concepts
- **Static nested:** `static class Report` inside `Engine` is an ordinary class with the name `Engine.Report`. It needs no `Engine` object. Make it `private` to hide it completely.
- **Inner:** `class Spark` without `static` belongs to one `Engine` object (`engine.new Spark()`) and can read that engine's fields.
- **Local:** a local class is declared inside a method and only visible there.
- **Anonymous:** `new Greeter() { ... }` defines and creates an unnamed class in one expression. It can use local variables that are effectively final.
- **Which to choose:** prefer **static** nested classes. An inner class silently keeps its outer object alive.

## Exercises
`Exercises.java` (run `java -cp out topic19_nested_and_anonymous_classes.Exercises`):
1. A `LinkedStack` built from a private static `Node`
2. A `Checker` written as an anonymous class that remembers `min` and `max`

## Common mistakes
- An inner class where a static nested one would do: it keeps the outer object in memory.
- `new Engine.Spark()` without an `Engine` object, which doesn't compile.
- Changing a local variable used by an anonymous class: it must be effectively final.

## Related topics
- [20 Lambdas](../topic20_lambdas_and_functional_interfaces/): the short form of an anonymous class
- [54 Linked lists](../../10-dsa/topic54_linked_lists_and_two_pointers/): nodes again

## Revision checklist
- [ ] I can explain static nested vs inner class, and which to prefer.
- [ ] I can write an anonymous class implementing an interface.
- [ ] I know which local variables an anonymous class may use.
