package topic07_terminal_operations_and_reduce;

import java.util.Comparator;
import java.util.List;

/*
 * Topic    : Terminal operations and reduce()
 * Key idea : a terminal operation ends the stream and produces a result (a value, a list,
 *            or a side effect). reduce() combines all elements into ONE value:
 *            reduce(start, (runningTotal, next) -> newTotal).
 * Run      : java -ea -cp out topic07_terminal_operations_and_reduce.TerminalOperations
 * Try this : use reduce() to find the longest course name.
 */
public class TerminalOperations {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        // reduce: how sum works, step by step
        int sum = numbers.stream().reduce(0, (total, n) -> total + n);
        int sumWithMethodRef = numbers.stream().reduce(0, Integer::sum);
        int product = List.of(1, 2, 3, 4).stream().reduce(1, (a, b) -> a * b);   // start at 1 for *
        int max = numbers.stream().reduce(Integer.MIN_VALUE, Math::max);
        System.out.println("reduce sum = " + sum + ", Integer::sum = " + sumWithMethodRef
                + ", product 1..4 = " + product + ", max = " + max);

        // print each step of reduce to see the running total
        System.out.print("steps: ");
        numbers.stream().limit(4).reduce(0, (total, n) -> {
            System.out.print(total + "+" + n + " ");
            return total + n;
        });
        System.out.println();

        // count, min, max (min/max return Optional: the stream might be empty)
        System.out.println("count = " + numbers.stream().count()
                + ", min = " + numbers.stream().min(Comparator.naturalOrder()).orElseThrow()
                + ", max = " + numbers.stream().max(Integer::compare).orElseThrow());

        // matching - these stop early (short-circuit) once the answer is known
        System.out.println("anyMatch(> 14)  = " + numbers.stream().anyMatch(n -> n > 14));
        System.out.println("allMatch(> 0)   = " + numbers.stream().allMatch(n -> n > 0));
        System.out.println("noneMatch(< 0)  = " + numbers.stream().noneMatch(n -> n < 0));

        // finding
        System.out.println("findFirst even  = " + numbers.stream().filter(n -> n % 2 == 0).findFirst().orElse(-1));

        // collecting to a list: toList() (Java 16+) is unmodifiable
        List<Integer> evens = numbers.stream().filter(n -> n % 2 == 0).toList();
        System.out.println("toList          = " + evens);

        // sum of squares of odd numbers - map + reduce together
        int sumOfOddSquares = numbers.stream().filter(n -> n % 2 != 0).map(n -> n * n).reduce(0, Integer::sum);
        System.out.println("sum of odd squares = " + sumOfOddSquares);

        assert sum == 77 && sum == sumWithMethodRef;
        assert product == 24 && max == 15;
        assert sumOfOddSquares == 81 + 169 + 225;
    }
}
