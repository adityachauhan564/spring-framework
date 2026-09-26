package topic24_modern_java;

import java.util.List;
import java.util.Map;

/*
 * Topic    : Modern Java (10 - 21) features you will see in real code
 * Key idea : less boilerplate - var, records, switch expressions, text blocks,
 *            pattern matching, sealed types, immutable collection factories.
 * Run      : java -cp out topic24_modern_java.ModernJavaFeatures
 * Try this : add a Triangle to Shape - the compiler forces you to update area().
 */
public class ModernJavaFeatures {

    // record (Java 16): constructor, getters, equals, hashCode and toString for free
    record Point(int x, int y) {
        Point {                                      // compact constructor: validation
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("coordinates must be >= 0");
            }
        }
    }

    // sealed interface (Java 17): only these classes may implement Shape
    sealed interface Shape permits Circle, Square { }

    record Circle(double radius) implements Shape { }

    record Square(double side) implements Shape { }

    // pattern matching for switch (Java 21): type check + variable in one step
    static double area(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Square s -> s.side() * s.side();
        };                                           // no default needed: Shape is sealed
    }

    static String dayType(String day) {
        // switch expression (Java 14): returns a value, no fall-through, no break
        return switch (day) {
            case "SAT", "SUN" -> "weekend";
            case "MON", "TUE", "WED", "THU", "FRI" -> "weekday";
            default -> "unknown";
        };
    }

    public static void main(String[] args) {
        // var (Java 10): the compiler infers the type - still statically typed
        var names = List.of("Asha", "Ravi");        // List<String>
        var count = names.size();                   // int
        System.out.println("var: " + names + ", count " + count);

        // records
        var p1 = new Point(1, 2);
        var p2 = new Point(1, 2);
        System.out.println("record: " + p1 + ", x = " + p1.x() + ", equals: " + p1.equals(p2));

        // switch expression
        System.out.println("SUN is a " + dayType("SUN") + ", MON is a " + dayType("MON"));

        // sealed + pattern matching switch
        List<Shape> shapes = List.of(new Circle(1), new Square(2));
        for (Shape shape : shapes) {
            System.out.printf("area of %s = %.2f%n", shape, area(shape));
        }

        // pattern matching for instanceof (Java 16)
        Object value = "hello";
        if (value instanceof String text && text.length() > 3) {
            System.out.println("instanceof pattern: " + text.toUpperCase());
        }

        // text block (Java 15): multi-line strings without \n and +
        String json = """
                {
                  "name": "Aditya",
                  "topic": 24
                }
                """;
        System.out.print("text block:\n" + json);

        // immutable collection factories (Java 9)
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
