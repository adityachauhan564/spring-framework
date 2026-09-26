package topic01_structured_vs_functional;

import java.util.List;

/*
 * Topic    : Functional (declarative) style - the "WHAT"
 * Key idea : you describe the result: "stream the numbers, keep the even ones, print them".
 *            The loop is hidden inside the stream.
 * Run      : java -cp out topic01_structured_vs_functional.FunctionalApproach
 * Try this : print only numbers greater than 10, in both styles.
 */
public class FunctionalApproach {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 6, 13, 19, 27, 31);

        System.out.println("All numbers:");
        numbers.stream()
                .forEach(System.out::println);         // method reference = x -> System.out.println(x)

        System.out.println("Even numbers:");
        numbers.stream()
                .filter(number -> number % 2 == 0)     // lambda: keep a number when this is true
                .forEach(System.out::println);
    }
}
