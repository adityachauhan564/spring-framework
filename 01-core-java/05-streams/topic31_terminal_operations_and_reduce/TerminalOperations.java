package topic31_terminal_operations_and_reduce;

import java.util.Comparator;
import java.util.List;

/*
 * Topic    : Terminal operations and reduce()
 * Key idea : A terminal operation is the LAST station of the conveyor belt. It ends the stream
 *            and gives you a final result - a number, a list, or an action like printing.
 *            reduce() squeezes ALL items into ONE value:
 *              reduce(startValue, (runningTotal, nextItem) -> newTotal)
 *            Like a shopkeeper adding up your bill item by item:
 *            start at 0, add 40 for milk -> 40, add 20 for bread -> 60, and so on.
 * Run      : java -ea -cp out topic31_terminal_operations_and_reduce.TerminalOperations
 *            (-ea switches on the "assert" checks at the end)
 * Try this : Use reduce() to find the longest course name.
 */
public class TerminalOperations {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        // reduce: how a "sum" actually works, step by step
        int sum = numbers.stream().reduce(0, (total, n) -> total + n);
        int sumWithMethodRef = numbers.stream().reduce(0, Integer::sum);           // same thing, shorter
        int product = List.of(1, 2, 3, 4).stream().reduce(1, (a, b) -> a * b);   // for multiply, start at 1 (starting at 0 would make everything 0)
        int max = numbers.stream().reduce(Integer.MIN_VALUE, Math::max);
        System.out.println("reduce sum = " + sum + ", Integer::sum = " + sumWithMethodRef
                + ", product 1..4 = " + product + ", max = " + max);

        // print every step of reduce, so you can watch the running total grow
        System.out.print("steps: ");
        numbers.stream().limit(4).reduce(0, (total, n) -> {
            System.out.print(total + "+" + n + " ");
            return total + n;
        });
        System.out.println();

        // count, min, max. min and max return an Optional, because the stream could be empty
        System.out.println("count = " + numbers.stream().count()
                + ", min = " + numbers.stream().min(Comparator.naturalOrder()).orElseThrow()
                + ", max = " + numbers.stream().max(Integer::compare).orElseThrow());

        // matching - these stop early as soon as the answer is known (short-circuit)
        System.out.println("anyMatch(> 14)  = " + numbers.stream().anyMatch(n -> n > 14));   // is there AT LEAST ONE?
        System.out.println("allMatch(> 0)   = " + numbers.stream().allMatch(n -> n > 0));    // are ALL of them?
        System.out.println("noneMatch(< 0)  = " + numbers.stream().noneMatch(n -> n < 0));   // is there NOT EVEN ONE?

        // finding - orElse(-1) gives -1 if nothing is found
        System.out.println("findFirst even  = " + numbers.stream().filter(n -> n % 2 == 0).findFirst().orElse(-1));

        // collecting into a list: toList() (Java 16+) gives a list you can't change
        List<Integer> evens = numbers.stream().filter(n -> n % 2 == 0).toList();
        System.out.println("toList          = " + evens);

        // sum of the squares of the odd numbers - filter, map and reduce working together
        int sumOfOddSquares = numbers.stream().filter(n -> n % 2 != 0).map(n -> n * n).reduce(0, Integer::sum);
        System.out.println("sum of odd squares = " + sumOfOddSquares);

        assert sum == 77 && sum == sumWithMethodRef;
        assert product == 24 && max == 15;
        assert sumOfOddSquares == 81 + 169 + 225;
    }
}
