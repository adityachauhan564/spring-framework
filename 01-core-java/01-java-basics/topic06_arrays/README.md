# Topic 06 · Arrays

**Difficulty:** Beginner · **Needs:** [04 Control flow](../topic04_control_flow/) · **Next:** [07 Methods](../topic07_methods/)

## Why it matters
A program rarely handles one value. It handles all the scores, every pixel, each line of a file. An array is Java's most basic way to keep many values of one type together, and it's what `ArrayList`, strings and much of the JDK are built on. Its two traps, fixed size and reference copying, come back in every later topic.

## What you'll learn
- Creating arrays, indexes from 0, default values, and `length`
- Looping with an index vs a for-each loop
- The `Arrays` helpers: `toString`, `sort`, `fill`, `copyOf`, `equals`, `binarySearch`
- Why `int[] b = a;` is not a copy
- 2D arrays and jagged arrays

## Run it
From `01-java-basics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic06_arrays.ArraysDemo
java -cp out topic06_arrays.TwoDimensionalArrays
```

## Key concepts
- **Size and indexes:** the size is fixed when the array is created. Valid indexes are `0` to `length - 1`, and `length` has no `()`.
- **Default values:** a new array is filled with defaults: `0`, `false` or `null`.
- **Arrays are objects:** assigning one copies the **reference**, so both names point at the same array. Use `Arrays.copyOf` for a real copy.
- **Printing and comparing:** `Arrays.toString` to print (`deepToString` for 2D), `Arrays.equals` to compare contents.
- **2D arrays:** `grid[row][col]`. `grid.length` is the number of rows, and `grid[r].length` the columns in row r. Rows can differ in length (jagged).

## Exercises
`Exercises.java` (run `java -cp out topic06_arrays.Exercises`):
1. The second-largest value in one pass
2. A reversed copy that leaves the original alone
3. Transpose a matrix

## Common mistakes
- `ArrayIndexOutOfBoundsException` from `i <= arr.length` instead of `i < arr.length`.
- `a.equals(b)` on arrays: it compares references. Use `Arrays.equals`.
- `System.out.println(arr)` prints something like `[I@1b6d3586`. Use `Arrays.toString(arr)`.
- Changing the "copy" and finding the original changed too.

## Related topics
- [07 Methods](../topic07_methods/): passing an array to a method
- [23 Lists and iteration](../../04-collections-and-generics/topic23_lists_and_iteration/): a list that grows
- [53 Arrays and hashing patterns](../../10-dsa/topic53_arrays_and_hashing/): interview problems on arrays

## Revision checklist
- [ ] I can explain why `int[] b = a;` isn't a copy, and make a real one.
- [ ] I print and compare arrays with the `Arrays` helpers.
- [ ] I can loop over a 2D array and explain `grid.length` vs `grid[0].length`.
