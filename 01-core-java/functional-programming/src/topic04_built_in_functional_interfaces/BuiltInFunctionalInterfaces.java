package topic04_built_in_functional_interfaces;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/*
 * Topic    : The built-in functional interfaces in java.util.function
 * Key idea : you rarely write your own - Java already has one for each "shape":
 *
 *   Predicate<T>        T -> boolean   test()     used by filter()
 *   Function<T, R>      T -> R         apply()    used by map()
 *   Consumer<T>         T -> void      accept()   used by forEach()
 *   Supplier<T>         () -> T        get()      used by generate(), orElseGet()
 *   BiFunction<T, U, R> (T, U) -> R    apply()
 *   UnaryOperator<T>    T -> T         (a Function with the same in/out type)
 *   BinaryOperator<T>   (T, T) -> T    used by reduce()
 *
 * Run      : java -cp out topic04_built_in_functional_interfaces.BuiltInFunctionalInterfaces
 * Try this : build a Predicate<String> "isLongCourse" and combine it with isSpring using or().
 */
public class BuiltInFunctionalInterfaces {

    public static void main(String[] args) {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Function<String, Integer> length = text -> text.length();
        Consumer<String> printer = text -> System.out.println("  consumed: " + text);
        Supplier<Double> random = () -> Math.random();
        BiFunction<Integer, Integer, String> describeSum = (a, b) -> a + " + " + b + " = " + (a + b);
        UnaryOperator<String> shout = text -> text.toUpperCase() + "!";
        BinaryOperator<Integer> multiply = (a, b) -> a * b;

        System.out.println("Predicate  isEven.test(4)         = " + isEven.test(4));
        System.out.println("Function   length.apply(\"Java\")   = " + length.apply("Java"));
        printer.accept("Consumer");
        System.out.println("Supplier   random.get() < 1       = " + (random.get() < 1));
        System.out.println("BiFunction describeSum.apply(2, 3) = " + describeSum.apply(2, 3));
        System.out.println("UnaryOp    shout.apply(\"hi\")      = " + shout.apply("hi"));
        System.out.println("BinaryOp   multiply.apply(4, 5)   = " + multiply.apply(4, 5));

        // --- composing: build bigger functions from small ones ---
        Predicate<String> isSpring = course -> course.contains("Spring");
        Predicate<String> isShort = course -> course.length() < 8;
        System.out.println("\nisSpring.and(isShort) \"Spring\"      = " + isSpring.and(isShort).test("Spring"));
        System.out.println("isSpring.and(isShort) \"Spring Boot\" = " + isSpring.and(isShort).test("Spring Boot"));
        System.out.println("isSpring.negate()     \"Docker\"      = " + isSpring.negate().test("Docker"));

        Function<Integer, Integer> doubleIt = n -> n * 2;
        Function<Integer, Integer> addTen = n -> n + 10;
        System.out.println("doubleIt.andThen(addTen).apply(5) = " + doubleIt.andThen(addTen).apply(5) + "   (5*2)+10");
        System.out.println("doubleIt.compose(addTen).apply(5) = " + doubleIt.compose(addTen).apply(5) + "   (5+10)*2");
    }
}
