package topic10_data_types_and_operators;

/*
 * Topic    : Primitive types, wrapper classes and type casting
 * Key idea : 8 primitives hold raw values (int, double, ...). Wrappers (Integer, Double)
 *            are objects, needed for collections. Java converts between them (autoboxing).
 * Run      : java -cp out topic10_data_types_and_operators.DataTypes
 * Try this : print Long.MAX_VALUE + 1 and explain the result.
 */
public class DataTypes {

    public static void main(String[] args) {
        // --- the 8 primitive types ---
        byte b = 127;                 // 8 bit   -128 .. 127
        short s = 32_000;             // 16 bit  (underscores only help readability)
        int i = 2_000_000_000;        // 32 bit  the default for whole numbers
        long l = 9_000_000_000L;      // 64 bit  needs the L suffix
        float f = 3.14f;              // 32 bit  needs the f suffix
        double d = 3.14159;           // 64 bit  the default for decimals
        char c = 'A';                 // 16 bit  one Unicode character, single quotes
        boolean flag = true;          // true / false only
        System.out.println(b + " " + s + " " + i + " " + l + " " + f + " " + d + " " + c + " " + flag);

        // --- widening: small -> big type, automatic and safe ---
        int small = 100;
        long bigger = small;
        double decimal = small;
        System.out.println("Widening:  " + bigger + ", " + decimal);

        // --- narrowing: big -> small type, needs a cast, may lose data ---
        double price = 99.99;
        int rounded = (int) price;                      // cuts the decimal part, does NOT round
        System.out.println("Narrowing: (int) 99.99 = " + rounded);
        System.out.println("Overflow:  (byte) 130  = " + (byte) 130);   // wraps around to -126
        System.out.println("Rounding:  Math.round(99.99) = " + Math.round(price));

        // --- char is a number too ---
        System.out.println("'A' + 1 = " + ('A' + 1) + ", as char: " + (char) ('A' + 1));

        // --- wrapper classes and autoboxing ---
        Integer boxed = 42;             // autoboxing:   int -> Integer
        int unboxed = boxed;            // unboxing:     Integer -> int
        int parsed = Integer.parseInt("123");
        System.out.println("Boxed " + boxed + ", unboxed " + unboxed + ", parsed " + parsed);

        // Gotcha: compare wrapper objects with equals(), not ==
        Integer x = 1000;
        Integer y = 1000;
        System.out.println("x == y      : " + (x == y) + "   (outside the -128..127 cache: two different objects)");
        System.out.println("x.equals(y) : " + x.equals(y));

        // Gotcha: unboxing a null wrapper throws NullPointerException
        Integer missing = null;
        // int boom = missing;   // NullPointerException at runtime
        System.out.println("Null wrapper is safe only while it stays an object: " + missing);
    }
}
