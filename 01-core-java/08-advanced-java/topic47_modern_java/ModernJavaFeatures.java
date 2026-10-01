package topic47_modern_java;

import java.util.List;
import java.util.Map;

/*
 * Topic    : Modern Java (versions 10 to 21) - features you will see in real company code
 * Key idea : Newer Java means less boring code and safer types:
 *            var, switch expressions, text blocks, pattern matching, sealed types,
 *            and ready-made unchangeable collections.
 *            Like upgrading from a keypad phone to a smartphone - same calls, much less effort.
 *            (Records are topic 17. The shapes below are records, because that's how
 *            sealed families of classes are usually written.)
 * Run      : java -cp out topic47_modern_java.ModernJavaFeatures
 * Try this : Add a Triangle to Shape - the compiler will force you to update area().
 */
public class ModernJavaFeatures {

    // sealed interface (Java 17): ONLY these listed classes are allowed to implement Shape.
    // Like a members-only club - the guest list is fixed
    sealed interface Shape permits Circle, Square { }

    record Circle(double radius) implements Shape { }

    record Square(double side) implements Shape { }

    // pattern matching for switch (Java 21): check the type AND get a variable of that type, in one step
    static double area(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Square s -> s.side() * s.side();
        };                                           // no default needed: Shape is sealed, so Java knows every case
    }

    static String dayType(String day) {
        // switch expression (Java 14): gives back a value, no fall-through, no break needed
        return switch (day) {
            case "SAT", "SUN" -> "weekend";
            case "MON", "TUE", "WED", "THU", "FRI" -> "weekday";
            default -> "unknown";
        };
    }

    public static void main(String[] args) {
        // var (Java 10): the compiler works out the type for you. The type is still fixed - it's not "any type"
        var names = List.of("Asha", "Ravi");        // the compiler sees a List<String>
        var count = names.size();                   // the compiler sees an int
        System.out.println("var: " + names + ", count " + count);

        // switch expression
        System.out.println("SUN is a " + dayType("SUN") + ", MON is a " + dayType("MON"));

        // sealed types + pattern matching switch, working together
        List<Shape> shapes = List.of(new Circle(1), new Square(2));
        for (Shape shape : shapes) {
            System.out.printf("area of %s = %.2f%n", shape, area(shape));
        }

        // pattern matching for instanceof (Java 16): "if value is a String, call it text" - no separate cast
        Object value = "hello";
        if (value instanceof String text && text.length() > 3) {
            System.out.println("instanceof pattern: " + text.toUpperCase());
        }

        // text block (Java 15): a multi-line string written as it looks - no \n and no + needed
        String json = """
                {
                  "name": "Aditya",
                  "topic": 47
                }
                """;
        System.out.print("text block:\n" + json);

        // ready-made collections (Java 9): Map.of / List.of create collections that can never change
        var scores = Map.of("Asha", 95, "Ravi", 82);
        try {
            scores.put("Kiran", 70);
        } catch (UnsupportedOperationException e) {
            System.out.println("Map.of(...) is immutable -> UnsupportedOperationException");
        }

        // handy String methods (Java 11)
        System.out.println("repeat: " + "ab".repeat(3) + ", strip: '" + "  hi  ".strip() + "', isBlank: " + " ".isBlank());
    }
}
