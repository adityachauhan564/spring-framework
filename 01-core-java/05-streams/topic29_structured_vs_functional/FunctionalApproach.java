package topic29_structured_vs_functional;

import java.util.List;

/*
 * Topic    : Functional (declarative) style - you say "WHAT" you want
 * Key idea : You just describe the result: "take the numbers, keep the even ones, print them".
 *            The loop is still there, but hidden inside the stream - you don't write it.
 *            Like ordering on Zomato: you say "one paneer butter masala", not how to cook it.
 * Run      : java -cp out topic29_structured_vs_functional.FunctionalApproach
 * Try this : Print only the numbers greater than 10, in both styles.
 */
public class FunctionalApproach {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 6, 13, 19, 27, 31);

        System.out.println("All numbers:");
        numbers.stream()                               // stream() = put the numbers on a conveyor belt
                .forEach(System.out::println);         // method reference, same as x -> System.out.println(x)

        System.out.println("Even numbers:");
        numbers.stream()
                .filter(number -> number % 2 == 0)     // lambda: let a number pass only when this is true
                .forEach(System.out::println);
    }
}
