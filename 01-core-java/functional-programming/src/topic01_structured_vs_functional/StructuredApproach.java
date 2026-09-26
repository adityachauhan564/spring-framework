package topic01_structured_vs_functional;

import java.util.List;

/*
 * Topic    : Structured (imperative) style - the "HOW"
 * Key idea : you tell Java every step: loop, check, print. Compare with FunctionalApproach,
 *            which solves the same two tasks.
 * Run      : java -cp out topic01_structured_vs_functional.StructuredApproach
 * Next     : FunctionalApproach
 */
public class StructuredApproach {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 6, 13, 19, 27, 31);

        printAll(numbers);
        printEven(numbers);
    }

    private static void printAll(List<Integer> numbers) {
        System.out.println("All numbers:");
        for (int number : numbers) {
            System.out.println("  " + number);
        }
    }

    private static void printEven(List<Integer> numbers) {
        System.out.println("Even numbers:");
        for (int number : numbers) {
            if (number % 2 == 0) {
                System.out.println("  " + number);
            }
        }
    }
}
