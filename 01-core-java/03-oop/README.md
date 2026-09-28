# Module 03 · Object-oriented programming

**Difficulty:** Intermediate · **Needs:** [02 Objects and classes](../02-objects-and-classes/) · **Next:** [04 Collections and generics](../04-collections-and-generics/)

## Why this module
Module 02 taught you to make objects. This module is about designing them well:
- **the four pillars:** encapsulation, inheritance, polymorphism and abstraction;
- **the methods every object inherits**, and records that write them for you;
- **your own exceptions**;
- **lambdas**, where the module ends: the bridge from objects to the functional style of module 05.

Topic 14, interfaces with dependency injection, is the idea Spring is built on. Don't skip it.

| # | Topic | You'll be able to |
|---|---|---|
| 11 | [Encapsulation and access modifiers](./topic11_encapsulation_and_access_modifiers/) | protect an object's rules |
| 12 | [Inheritance and polymorphism](./topic12_inheritance_and_polymorphism/) | write code that works for every subclass |
| 13 | [Abstract classes](./topic13_abstract_classes/) | share code and force subclasses to fill the gaps |
| 14 | [Interfaces and dependency injection](./topic14_interfaces_and_dependency_injection/) | program to a contract and swap implementations |
| 15 | [equals, hashCode, toString](./topic15_equals_and_hashcode/) | make objects equal by value |
| 16 | [Enums](./topic16_enums/) | replace magic strings with a fixed set of constants |
| 17 | [Records and immutability](./topic17_records_and_immutability/) | build data objects that can't be changed by accident |
| 18 | [Custom exceptions](./topic18_custom_exceptions/) | report domain errors clearly |
| 19 | [Nested and anonymous classes](./topic19_nested_and_anonymous_classes/) | read and write classes inside classes |
| 20 | [Lambdas and functional interfaces](./topic20_lambdas_and_functional_interfaces/) | pass behaviour as a value |
| 21 | [Built-in functional interfaces](./topic21_built_in_functional_interfaces/) | use Predicate, Function, Consumer, Supplier |
| 22 | [Method references](./topic22_method_references/) | write the shortest readable lambda |

## Compile and run
From this folder (`01-core-java/03-oop`):
```bash
javac -d out $(find . -name "*.java")            # PowerShell: javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out topic14_interfaces_and_dependency_injection.Main
java -cp out topic14_interfaces_and_dependency_injection.Exercises
```
Each topic works the same way as in [module 01](../01-java-basics/#how-to-work-through-a-topic): README, examples, `Exercises.java`, `solutions/`, checklist.

## Module checklist
- [ ] I keep fields private and validate before changing them.
- [ ] I can explain which method runs in `Animal a = new Dog()`, and why `totalArea` needs no `instanceof`.
- [ ] I can choose between an abstract class and an interface, and explain dependency injection.
- [ ] I override `equals` and `hashCode` together, or use a record.
- [ ] I can write a custom exception that carries data and keeps its cause.
- [ ] I can turn an anonymous class into a lambda, and a lambda into a method reference.
