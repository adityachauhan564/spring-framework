# Topic 13 · Abstract classes

**Difficulty:** Intermediate · **Needs:** [12 Inheritance and polymorphism](../topic12_inheritance_and_polymorphism/) · **Next:** [14 Interfaces and dependency injection](../topic14_interfaces_and_dependency_injection/)

## Why it matters
In topic 12 a plain `Shape` could be created and returned an area of 0, which is meaningless. Some parents only make sense as a *template*: "every printer can print twice, but each kind prints in its own way". An abstract class says exactly that. It holds the shared code, and forces each subclass to fill in the part only it knows. The compiler stops anyone from forgetting.

## What you'll learn
- Abstract methods (no body) and concrete methods in the same class
- Why an abstract class can't be created with `new`
- The template-method idea: shared code that calls the abstract step

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic13_abstract_classes.AbstractClassDemo
```
Read `Printer`, then `ConsolePrinter`, then the demo.

## Key concepts
- **Abstract methods:** `abstract void print(String message);` has no body, and every concrete subclass must implement it, or the code doesn't compile.
- **Shared code:** `printTwice` is written once and calls `print`. At runtime it runs the subclass's version.
- **No `new`:** you can't write `new Printer()`, but `Printer p = new ConsolePrinter();` is fine.
- **Abstract class vs interface:** an abstract class can hold **state** (fields) and constructors. A class can extend only **one** of them, but it can implement many interfaces (next topic).

## Exercises
`Exercises.java` (run `java -cp out topic13_abstract_classes.Exercises`):
1. Make `Employee.monthlyPay()` abstract, and implement it for salaried and hourly employees
2. See that `payslip()`, written once, works for both

## Common mistakes
- Trying to `new` an abstract class.
- Giving the abstract method a dummy body (`return 0`) instead of making it abstract, so subclasses can silently forget it.
- Using an abstract class where an interface would do, and spending the one `extends` a class gets.

## Related topics
- [12 Inheritance](../topic12_inheritance_and_polymorphism/): overriding
- [14 Interfaces](../topic14_interfaces_and_dependency_injection/): contracts without state
- [49 Design patterns](../../08-advanced-java/topic49_design_patterns/): Template Method and Strategy

## Revision checklist
- [ ] I can explain when to use an abstract class and when an interface.
- [ ] I know what the compiler forces a subclass of an abstract class to do.
- [ ] I can write a shared method that calls an abstract one.
