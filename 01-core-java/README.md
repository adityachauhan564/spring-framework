# 01 - Core Java

Plain Java fundamentals, with no framework: OOP, collections, exceptions, lambdas and streams, basic DSA patterns, sockets and a first unit test. None of these projects has a build file; each README gives the exact `javac`/`java` commands. Next stage: [02 - Spring Foundations](../02-spring-foundations/), where the container does the object wiring you do by hand here.

| # | Project | Topics | Status |
| :- | :--- | :--- | :--- |
| 1 | [head-first-java](./head-first-java/) | 9 numbered topics: loops, objects, ArrayList, abstract classes, interfaces + DI, exceptions, HashMap/Optional (+ build your own), streams, TwoSum | ✅ Working |
| 2 | [functional-programming](./functional-programming/) | Loops vs streams, lambdas, method references, Optional | ✅ Working |
| 3 | [dsa-interview-practice](./dsa-interview-practice/) | Kadane, max product subarray, linked lists, lambdas, threads, Stream API | 🚧 Partial |
| 4 | [junit-basics](./junit-basics/) | First JUnit 5 test, `assertEquals` | ✅ Working |
| 5 | [multithreaded-web-server](./multithreaded-web-server/) | `ServerSocket`, try-with-resources, flushing | 🚧 Step 1 |

## Suggested study order
1. **head-first-java**: work through topic01 to topic09 in order.
2. **functional-programming**: rewrite a loop as a stream.
3. **dsa-interview-practice** `src/`: lambdas, functional interfaces and the Stream API in more depth.
4. **dsa-interview-practice** DSA folder: Kadane, then linked lists. Finish the TODO stubs as practice.
5. **junit-basics**: turn your `main`-method checks into real tests.
6. **multithreaded-web-server**: sockets, then add a thread per client.

## Quick revision checklist
- [ ] Explain abstraction with the `PaymentService` / `OrderService` example, and why it enables swapping implementations
- [ ] Checked vs unchecked exceptions, with one example of each
- [ ] `List.of` vs `Arrays.asList` vs `new ArrayList<>()` mutability
- [ ] What a functional interface is and how a lambda targets it
- [ ] Stream pipeline: intermediate vs terminal operations, laziness
- [ ] Safe `Optional` usage (no bare `get()`)
- [ ] Kadane's algorithm in O(n) / O(1), and why max product needs both max and min
- [ ] Why an unflushed `PrintWriter` sends nothing, and how try-with-resources prevents socket leaks
