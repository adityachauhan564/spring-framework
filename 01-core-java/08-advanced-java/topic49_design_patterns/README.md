# Topic 49 · Design patterns

**Difficulty:** Advanced · **Needs:** [14 Interfaces and DI](../../03-oop/topic14_interfaces_and_dependency_injection/), [37 Higher-order functions](../../05-streams/topic37_higher_order_functions/) · **Next:** [50 Unit testing](../../09-testing-and-build/)

## Why it matters
Design patterns are named solutions to problems that keep coming up:
- "there must be only one of these";
- "choose the implementation at runtime";
- "this object has fifteen optional settings";
- "tell everyone when this happens".

Knowing the names lets you talk about designs in one word, and recognise them in frameworks. Spring is built on exactly these five: its container is a factory that makes singletons, injects strategies, publishes events to observers, and hands you builders.

## What you'll learn
- **Singleton:** one shared instance (and why Spring does it for you)
- **Factory:** one place decides which class to create
- **Builder:** readable construction of objects with many optional parts
- **Strategy:** swap an algorithm by passing in a different implementation or lambda
- **Observer:** notify interested listeners without knowing who they are

## Run it
From `08-advanced-java` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic49_design_patterns.SingletonPattern
java -cp out topic49_design_patterns.FactoryPattern
java -cp out topic49_design_patterns.BuilderPattern
java -cp out topic49_design_patterns.StrategyPattern
java -cp out topic49_design_patterns.ObserverPattern
```
Each file's header says when to use the pattern and where Spring uses it.

## Key concepts

| Pattern | Problem | Shape | In Spring |
|---|---|---|---|
| Singleton | one instance only | private constructor + static instance (or a one-constant enum) | every bean, by default |
| Factory | pick the class at runtime | a method returning the interface type | the container itself |
| Builder | many optional parameters | chained setters returning `this`, then `build()` | `RestClient.builder()` |
| Strategy | interchangeable algorithms | an interface injected into the user | injecting an implementation |
| Observer | react to events | a list of listeners, notify each | `@EventListener`, messaging |

Patterns are tools, not goals. Use one when its problem is really there.

## Exercises
`Exercises.java` (run `java -cp out topic49_design_patterns.Exercises`):
1. Payment strategies from a factory, written as lambdas
2. A pizza builder with a default and optional toppings
3. A price ticker that notifies observers

## Common mistakes
- Forcing a pattern where a plain method would do.
- Hand-written singletons everywhere. They're global state, and hard to replace in tests; let the DI container manage "one instance".
- A factory with a growing `if/else` in every caller. Keep the choice in **one** place.
- Observers that are never removed, which leaks memory ([48](../topic48_jvm_memory/)).

## Related topics
- [14 Interfaces and DI](../../03-oop/topic14_interfaces_and_dependency_injection/): Strategy + DI by hand
- [02 Spring Foundations](../../../02-spring-foundations/): where the container takes over

## Revision checklist
- [ ] I can name the problem each of the five patterns solves.
- [ ] I can say which pattern Spring's container replaces.
- [ ] I can write a builder whose methods return `this`.
