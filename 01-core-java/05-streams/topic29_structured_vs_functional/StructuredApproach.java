package topic29_structured_vs_functional;

import java.util.List;

/*
 * Topic    : Structured (imperative) style - you explain the "HOW"
 * Key idea : You tell Java every single step: loop, check, print.
 *            Like giving a new cook full instructions: "take the pan, put oil, wait 1 minute, add jeera..."
 *            Compare this file with FunctionalApproach, which does the same two jobs.
 * Run      : java -cp out topic29_structured_vs_functional.StructuredApproach
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
        for (int number : numbers) {                // step 1: go through each number
            System.out.println("  " + number);      // step 2: print it
        }
    }

    private static void printEven(List<Integer> numbers) {
        System.out.println("Even numbers:");
        for (int number : numbers) {                // step 1: go through each number
            if (number % 2 == 0) {                  // step 2: check if it is even
                System.out.println("  " + number);  // step 3: print it
            }
        }
    }
}
