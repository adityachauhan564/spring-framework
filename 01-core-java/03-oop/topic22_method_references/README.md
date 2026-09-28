# Topic 22 · Method references

**Difficulty:** Intermediate · **Needs:** [21 Built-in functional interfaces](../topic21_built_in_functional_interfaces/) · **Next:** [23 Lists and iteration](../../04-collections-and-generics/topic23_lists_and_iteration/)

## Why it matters
Many lambdas do nothing but call one existing method: `s -> s.toUpperCase()`, `x -> System.out.println(x)`. A method reference names that method directly (`String::toUpperCase`), which is shorter and often easier to read. You'll see `::` constantly from now on: in streams, comparators and Spring code.

## What you'll learn
The four kinds of method reference:

| Kind | Example | Same as |
|---|---|---|
| static method | `Integer::parseInt` | `s -> Integer.parseInt(s)` |
| method of a particular object | `System.out::println` | `x -> System.out.println(x)` |
| method of the argument itself | `String::toUpperCase` | `s -> s.toUpperCase()` |
| constructor | `StringBuilder::new` | `s -> new StringBuilder(s)` |

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic22_method_references.MethodReferences
```

## Key concepts
- **Type-checking:** a method reference fits a functional interface when the method's parameters and return type match the interface's one method.
- **`Type::instanceMethod`** uses the *first argument* as the object: `String::startsWith` fits `(s, prefix) -> s.startsWith(prefix)`.
- **Which to use:** use a method reference when it reads better, and a lambda when you need extra logic or clearer names.

## Exercises
`Exercises.java` (run `java -cp out topic22_method_references.Exercises`): turn five lambdas into method references, covering all four kinds.

## Common mistakes
- Forcing a method reference where the lambda is clearer.
- Confusing `String::length` (the argument's method) with a static method.
- Ambiguity with overloaded methods: fall back to a lambda.

## Related topics
- [28 Sorting](../../04-collections-and-generics/topic28_sorting/): `Comparator.comparing(Employee::name)`
- [30 Intermediate operations](../../05-streams/topic30_intermediate_operations/): `.map(String::toUpperCase)`

## Revision checklist
- [ ] I can list the 4 kinds with one example each.
- [ ] I can say which lambda a method reference stands for.
- [ ] I know when a lambda is the better choice.
