package topic05_method_references;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/*
 * Topic    : Method references (::)
 * Key idea : when a lambda only calls one existing method, name the method instead.
 *            There are 4 kinds:
 *
 *   ClassName::staticMethod      Integer::parseInt       s -> Integer.parseInt(s)
 *   object::instanceMethod       System.out::println     x -> System.out.println(x)
 *   ClassName::instanceMethod    String::toUpperCase     s -> s.toUpperCase()
 *   ClassName::new               ArrayList::new          () -> new ArrayList<>()
 *
 * Run      : java -cp out topic05_method_references.MethodReferences
 * Try this : replace course -> course.length() below with a method reference.
 */
public class MethodReferences {

    static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static void main(String[] args) {
        List<String> courses = List.of("spring", "api", "docker");

        // 1. static method
        List.of("1", "22", "333").stream()
                .map(Integer::parseInt)                   // s -> Integer.parseInt(s)
                .filter(MethodReferences::isEven)         // your own static methods work too
                .forEach(n -> System.out.println("static:     " + n));

        // 2. instance method of a particular object
        courses.forEach(System.out::println);             // x -> System.out.println(x)

        // 3. instance method of an arbitrary object of that type (the element itself)
        courses.stream()
                .map(String::toUpperCase)                 // s -> s.toUpperCase()
                .forEach(c -> System.out.println("arbitrary:  " + c));

        BiFunction<String, String, Boolean> startsWith = String::startsWith;   // (s, prefix) -> s.startsWith(prefix)
        System.out.println("\"spring\".startsWith(\"sp\") = " + startsWith.apply("spring", "sp"));

        // 4. constructor
        Supplier<List<String>> newList = ArrayList::new;                        // () -> new ArrayList<>()
        Function<String, StringBuilder> newBuilder = StringBuilder::new;        // s -> new StringBuilder(s)
        List<String> list = newList.get();
        list.add("made by ArrayList::new");
        System.out.println(list + ", " + newBuilder.apply("abc").reverse());

        // same result, lambda vs method reference - use the one that reads better
        courses.stream().map(course -> course.length()).forEach(n -> System.out.print(n + " "));
        System.out.println(" <- lengths via lambda");
    }
}
