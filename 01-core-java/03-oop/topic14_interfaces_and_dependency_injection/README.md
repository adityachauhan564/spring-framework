# Topic 14 · Interfaces and dependency injection

**Difficulty:** Intermediate · **Needs:** [13 Abstract classes](../topic13_abstract_classes/) · **Next:** [15 equals and hashCode](../topic15_equals_and_hashcode/)

## Why it matters
If `OrderService` creates `new UPIPayment()` itself, it's welded to UPI: adding card payments means editing it, and testing it means making real payments. An interface is a **contract** ("anything that can `pay(amount)`"). Passing the implementation in from outside, called dependency injection, lets the same `OrderService` work with UPI, cards, a wallet, or a fake in a test. This is the single most important idea to carry into Spring, whose core job is doing exactly this injection for you.

## What you'll learn
- Declaring and implementing an interface, and implementing several at once
- Programming to the interface type
- **Constructor injection**
- `default` and `static` methods in interfaces

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic14_interfaces_and_dependency_injection.Main
java -cp out topic14_interfaces_and_dependency_injection.DefaultMethods
```
Read `PaymentService`, then `UPIPayment` / `CreditCardPayment`, then `OrderService`, then `Main`.

## Key concepts
- **Interfaces:** an interface lists **what** can be done. Each class that implements it decides **how**.
- **Constructor injection:** `OrderService` depends only on `PaymentService` and receives it in its constructor, so it never calls `new` for its dependency.
- **Adding implementations:** a new `WalletPayment` needs **no** change to `OrderService`.
- **Testing:** in a test, you inject a fake that records calls.
- **Default and static methods:** a `default` method is inherited code, and implementations may override it. A `static` method belongs to the interface itself.
- **Several interfaces:** a class can `implements A, B`, but it can `extends` only one class.

## Exercises
`Exercises.java` (run `java -cp out topic14_interfaces_and_dependency_injection.Exercises`):
1. A `WalletPayment`, used by the unchanged `OrderService`
2. A `RecordingPayment` fake for tests

## Common mistakes
- `new UPIPayment()` inside the business class, which puts the coupling right back.
- Declaring variables as the concrete type (`UPIPayment p = ...`) when the interface would do.
- Interfaces with dozens of methods: keep contracts small.

## Related topics
- [13 Abstract classes](../topic13_abstract_classes/): when you need shared state
- [20 Lambdas](../topic20_lambdas_and_functional_interfaces/): an interface with one method can be implemented with a lambda
- [02 Spring Foundations](../../../02-spring-foundations/): the container that performs this injection for you

## Revision checklist
- [ ] I can explain why DI makes code easy to swap and to test.
- [ ] I can add an implementation without changing the code that uses the interface.
- [ ] I know what a default method is for.
