# 01 - Core Java

Plain Java fundamentals, with no framework: OOP, collections, generics, exceptions, threads, file I/O, functional programming (lambdas, streams, collectors, Optional), basic DSA patterns, sockets and a first unit test. None of these projects has a build file; each README gives the exact `javac`/`java` commands. Next stage: [02 - Spring Foundations](../02-spring-foundations/), where the container does the object wiring you do by hand here.

| # | Project | Topics | Status |
| :- | :--- | :--- | :--- |
| 1 | [head-first-java](./head-first-java/) | 24 numbered topics: data types, strings, arrays, methods/static, OOP (encapsulation, inheritance, abstract classes, interfaces), equals/hashCode, enums, generics, collections, sorting, exceptions, threads, file I/O, streams, modern Java | ✅ Working |
| 2 | [functional-programming](./functional-programming/) | 15 numbered topics: lambdas, functional interfaces, method references, stream operations, reduce, collectors, flatMap, Optional, laziness, parallel streams, capstone | ✅ Working |
| 3 | [dsa-interview-practice](./dsa-interview-practice/) | Kadane, max product subarray, linked lists, lambdas, threads, Stream API | 🚧 Partial |
| 4 | [junit-basics](./junit-basics/) | First JUnit 5 test, `assertEquals` | ✅ Working |
| 5 | [multithreaded-web-server](./multithreaded-web-server/) | `ServerSocket`, try-with-resources, flushing | 🚧 Step 1 |

## Suggested study order
1. **head-first-java**: follow the beginner path in its README (topic01 to topic24).
2. **functional-programming**: work through its 15 topics in order, ending with the capstone.
3. **dsa-interview-practice** `src/`: extra practice with lambdas, a thread demo and the Stream API.
4. **dsa-interview-practice** DSA folder: Kadane, then linked lists. Finish the TODO stubs as practice.
5. **junit-basics**: turn your `main`-method checks into real tests.
6. **multithreaded-web-server**: sockets, then add a thread per client.

## Quick revision checklist
Each item says where to revise it. The full checklists are in each project's README.

**Java fundamentals (head-first-java)**
- [ ] Primitives vs wrappers, casting, and why `Integer == Integer` can be false (topic10)
- [ ] String immutability, the String pool, `==` vs `equals`, and when to use StringBuilder (topic11)
- [ ] Pass-by-value for primitives vs object references, and `static` vs instance members (topic13)
- [ ] Encapsulation, inheritance, overriding vs overloading, and polymorphism (topics 14, 15, 04)
- [ ] Explain abstraction with the `PaymentService` / `OrderService` example, and why it lets you swap implementations (topic05)
- [ ] The `equals` / `hashCode` contract and what breaks in a HashSet without it (topic16)
- [ ] Choosing between List, Set, Queue/Deque and Map, including the Hash / LinkedHash / Tree variants (topics 03, 07, 19)
- [ ] Mutability: `new ArrayList<>()` can change (topic03), while `List.of` / `Map.of` are immutable (topic24). `Arrays.asList` is fixed-size: `set` works, `add` throws. There's no example of that one yet.
- [ ] Comparable vs Comparator, and generics with bounded types (topics 20, 18)
- [ ] Checked vs unchecked exceptions with one example of each, custom exceptions, and try-with-resources (topics 06, 21)
- [ ] Race conditions, `synchronized` vs `AtomicInteger`, and why to use an ExecutorService (topic22)

**Functional programming (functional-programming)**
- [ ] What a functional interface is, how a lambda targets it, and the built-in ones: Predicate, Function, Consumer, Supplier (topics 02, 04)
- [ ] The four kinds of method reference (topic05)
- [ ] Stream pipeline: intermediate vs terminal operations, laziness and short-circuiting (topics 06, 07, 12)
- [ ] `reduce`, `groupingBy` / `partitioningBy`, and `map` vs `flatMap` (topics 07, 08, 09)
- [ ] Safe `Optional` usage: no bare `get()`, and chaining with `map` / `flatMap` (topic11)
- [ ] When `parallel()` helps, and why shared mutable state breaks it (topic14)

**DSA, testing and networking**
- [ ] Kadane's algorithm in O(n) time / O(1) space, and why max product needs both max and min (dsa-interview-practice)
- [ ] TwoSum in O(n) with a HashSet (head-first-java topic09)
- [ ] Turn a `main`-method check into a JUnit test with `assertEquals` (junit-basics)
- [ ] Why an unflushed `PrintWriter` sends nothing, and how try-with-resources prevents socket leaks (multithreaded-web-server)
