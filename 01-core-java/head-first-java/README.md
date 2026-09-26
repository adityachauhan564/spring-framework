# Head First Java - Hands On

> Chapter exercises from *Head First Java*, plus small OOP, exception, collection and Stream interview snippets.

## What it teaches
- Classes, objects and instance variables (Chapter 2 guessing game, Chapter 4 `Song`)
- `ArrayList` basics: `add`, `size`, `contains` (Chapter 6)
- Abstraction with an interface + constructor injection (payment example)
- Checked vs unchecked exceptions
- `HashMap` iteration and `Optional.ofNullable`
- A real-world Stream pipeline: cleaning and de-duplicating phone numbers

## Run it
No build file - plain `javac`/`java` from this folder:

```bash
javac -d out $(find src -name "*.java")
java -cp out Chapter_2.GameLauncher
java -cp out Oops_Abstraction_Realworld_Example.Main
java -cp out Stream_API_Interview.Filter_phone_Numbers
java -cp out Exception.UncheckedException
```

In Eclipse: *File > New > Java Project*, untick "use default location" and point it at this folder (the `.classpath`/`.project` files are no longer tracked in git).

## Read the code in this order
1. `src/Chapter_2/GameLauncher.java`, `GuessGame.java`, `Player.java` - objects talking to objects
2. `src/Chapter_4/Song.java` - what "instance variables" and methods are (see Status)
3. `src/Chapter_6_Using_the_Java_Library/Egg.java` - first `ArrayList`
4. `src/Exception/Checked_Exception.java`, `UncheckedException.java` - compile-time vs runtime exceptions
5. `src/Oops_Abstraction_Realworld_Example/` - `PaymentService` interface, `UPIPayment`/`CreditCardPayment`, `OrderService` depends on the interface
6. `src/Java_Full_stack_Basics_11_Hours_Youtube/Collections_important.java` - `Map.Entry` loop, `null` keys, `Optional`
7. `src/Stream_API_Interview/Filter_phone_Numbers.java` - `filter` / `map` / `distinct` / `collect`
8. `src/D_Intrvw_Q/abs.java`, `abc_do.java` - abstract class + polymorphic reference

## Revision notes
- `OrderService` only knows `PaymentService`: swap `UPIPayment` for `CreditCardPayment` in `Main` without touching business logic - this is abstraction + dependency injection by hand (the idea Spring automates later).
- Checked exceptions (`FileNotFoundException`) must be caught or declared; unchecked ones (`NullPointerException`) need not be.
- `HashMap` allows one `null` key and `null` values; iteration order is not guaranteed.
- `Optional.ofNullable(map.get(k))` avoids a null check but prefer `ifPresentOrElse` over `isPresent()` + empty branch.
- The phone-number pipeline order matters: drop `null`s before `trim`, strip non-digits before checking length.
- `01` in `Collections_important.java` is an octal literal in Java - harmless here, a classic gotcha.
- `abs run = new abc_do();` - the reference type is the abstract class, the object is the subclass (runtime polymorphism).
- `FileReader` in `Checked_Exception.java` is never closed; real code should use try-with-resources.

## Status
🚧 Partial - most programs run.
- `Chapter_4/Song.java`: setters are empty and `title`/`artist` are local variables in `main`, not instance variables - finish it as an exercise.
- 📝 Practice stubs (marked `// TODO: not implemented yet`): `D_Intrvw_Q/TargetSum.java`, `D_Intrvw_Q/h_Map.java`, `Chapter_6_Using_the_Java_Library/Something_you_can_do_With_ArrayList.java`.
- `D_Intrvw_Q/Employee.java` is only two fields (its self-creating field that caused a `StackOverflowError` was removed).
