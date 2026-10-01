package topic22_method_references;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Topic    : Method references (::)
 * Key idea : If a lambda does nothing except call ONE existing method, you can just name that method.
 *            s -> s.toUpperCase()  becomes  String::toUpperCase
 *            Like saying "call Ramesh" instead of "dial 98xxxxxxx and ask for Ramesh".
 *            There are 4 kinds:
 *
 *   ClassName::staticMethod      Integer::parseInt       same as  s -> Integer.parseInt(s)
 *   object::instanceMethod       System.out::println     same as  x -> System.out.println(x)
 *   ClassName::instanceMethod    String::toUpperCase     same as  s -> s.toUpperCase()
 *   ClassName::new               StringBuilder::new      same as  s -> new StringBuilder(s)
 *
 * Run      : java -cp out topic22_method_references.MethodReferences
 * Try this : Replace the 'length' lambda at the end with a method reference.
 */
public class MethodReferences {

    static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static void main(String[] args) {
        // 1. a static method
        Function<String, Integer> parse = Integer::parseInt;           // s -> Integer.parseInt(s)
        Predicate<Integer> even = MethodReferences::isEven;           // works with your own static methods also
        System.out.println("static:     parse(\"42\") = " + parse.apply("42") + ", even(42) = " + even.test(42));

        // 2. a method of ONE PARTICULAR object (here: the System.out object)
        Consumer<String> print = System.out::println;                 // x -> System.out.println(x)
        print.accept("particular: printed through System.out::println");

        // 3. a method of "whichever object comes in" - the argument itself is the object that runs the method
        Function<String, String> upper = String::toUpperCase;         // s -> s.toUpperCase()
        BiFunction<String, String, Boolean> startsWith = String::startsWith;   // (s, p) -> s.startsWith(p)
        System.out.println("arbitrary:  " + upper.apply("spring") + ", \"spring\".startsWith(\"sp\") = "
                + startsWith.apply("spring", "sp"));

        // 4. a constructor
        Function<String, StringBuilder> newBuilder = StringBuilder::new;   // s -> new StringBuilder(s)
        Supplier<Object> newObject = Object::new;                          // () -> new Object()
        System.out.println("constructor: " + newBuilder.apply("abc").reverse() + ", " + (newObject.get() != null));

        // lambda or method reference - both give the same result. Use whichever is easier to read
        Function<String, Integer> length = text -> text.length();
        System.out.println("lambda:     length(\"docker\") = " + length.apply("docker"));
    }
}
