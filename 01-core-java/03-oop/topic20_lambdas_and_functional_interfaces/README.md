# Topic 20 · Lambdas and functional interfaces

**Difficulty:** Intermediate · **Needs:** [19 Nested and anonymous classes](../topic19_nested_and_anonymous_classes/) · **Next:** [21 Built-in functional interfaces](../topic21_built_in_functional_interfaces/)

## Why it matters
Topic 19's anonymous class took five lines to say "check that a value is between min and max". A lambda says it in one: `value -> value >= min && value <= max`. Lambdas let you pass **behaviour** as a value, like "how to compare", "what to do with each item", or "when to keep an element". That's the foundation of streams (module 05), of sorting with comparators, and of much of Spring's configuration.

## What you'll learn
- What makes an interface *functional*, and what `@FunctionalInterface` checks
- Turning an anonymous class into a lambda, and the syntax forms
- Passing lambdas to methods, and returning them
- Why captured local variables must be effectively final

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic20_lambdas_and_functional_interfaces.LambdaSyntax
```

## Key concepts
- **Functional interface:** one with exactly **one** abstract method (default and static methods don't count). A lambda is an implementation of that one method.
- **Syntax:**
  - `(a, b) -> a + b` has an expression body, with no `return`;
  - `(a, b) -> { ...; return x; }` has a block body;
  - `name -> ...` needs no brackets for a single parameter.
- **Types are inferred** from the interface the lambda is assigned to.
- **Capturing variables:** a lambda may use local variables from outside only if they're never reassigned (effectively final).

## Exercises
`Exercises.java` (run `java -cp out topic20_lambdas_and_functional_interfaces.Exercises`):
1. A `Calculator` lambda for powers
2. A `TextCheck` lambda
3. `combine`, which takes the operation as a parameter
4. A method that returns a lambda capturing a value

## Common mistakes
- Reassigning a variable that a lambda uses: a compile error.
- A lambda with a block body that forgets `return`.
- Adding a second abstract method to a functional interface, which breaks every lambda for it. `@FunctionalInterface` catches it.
- Very long lambdas: give the logic a name as a method instead (see [22](../topic22_method_references/)).

## Related topics
- [21 Built-in functional interfaces](../topic21_built_in_functional_interfaces/): you rarely need your own interface
- [29 Structured vs functional](../../05-streams/topic29_structured_vs_functional/): lambdas in streams
- [39 Threads](../../06-concurrency/topic39_threads/): a `Runnable` written as a lambda

## Revision checklist
- [ ] I can explain what makes an interface "functional".
- [ ] I can rewrite an anonymous class as a lambda.
- [ ] I know which local variables a lambda may use.
