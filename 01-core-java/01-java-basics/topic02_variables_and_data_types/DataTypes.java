package topic02_variables_and_data_types;

/*
 * Topic    : Primitive types, wrapper classes and type casting
 * Key idea : A variable is a named box that holds a value.
 *            - Java has 8 "primitive" types for simple raw values: int, double, char, boolean, etc.
 *            - Each primitive has a "wrapper" class that is an object: int -> Integer, double -> Double.
 *              Collections like ArrayList can only hold objects, so they need wrappers.
 *            - Java converts int <-> Integer for you automatically. This is called autoboxing.
 * Run      : java -cp out topic02_variables_and_data_types.DataTypes
 * Try this : Print Long.MAX_VALUE + 1 and explain why the answer is negative.
 */
public class DataTypes {

    public static void main(String[] args) {
        // --- the 8 primitive types (bigger box = bigger range of values) ---
        byte b = 127;                 // 8 bit   only -128 to 127
        short s = 32_000;             // 16 bit  (the _ is only for easy reading, like 32,000)
        int i = 2_000_000_000;        // 32 bit  the normal choice for whole numbers (about +/- 200 crore)
        long l = 9_000_000_000L;      // 64 bit  for very big numbers; put L at the end
        float f = 3.14f;              // 32 bit  decimal number; put f at the end
        double d = 3.14159;           // 64 bit  the normal choice for decimal numbers
        char c = 'A';                 // 16 bit  exactly one character, in single quotes
        boolean flag = true;          // only true or false, like a yes/no answer
        System.out.println(b + " " + s + " " + i + " " + l + " " + f + " " + d + " " + c + " " + flag);

        // --- widening: small box -> big box. Java does it automatically, nothing is lost ---
        // like pouring a glass of water into a bucket
        int small = 100;
        long bigger = small;
        double decimal = small;
        System.out.println("Widening:  " + bigger + ", " + decimal);

        // --- narrowing: big box -> small box. You must write a cast like (int), and data can be lost ---
        // like pouring a bucket into a glass - some water spills
        double price = 99.99;
        int rounded = (int) price;                      // just cuts off the decimal part: 99.99 becomes 99, NOT 100
        System.out.println("Narrowing: (int) 99.99 = " + rounded);
        System.out.println("Overflow:  (byte) 130  = " + (byte) 130);   // 130 does not fit in a byte, so it wraps around to -126
        System.out.println("Rounding:  Math.round(99.99) = " + Math.round(price));   // for proper rounding, use Math.round

        // --- a char is actually a number inside ('A' is 65) ---
        System.out.println("'A' + 1 = " + ('A' + 1) + ", as char: " + (char) ('A' + 1));

        // --- wrapper classes and autoboxing ---
        Integer boxed = 42;             // autoboxing: Java puts the int 42 inside an Integer object
        int unboxed = boxed;            // unboxing:   Java takes the int back out of the Integer
        int parsed = Integer.parseInt("123");   // turns the text "123" into the number 123
        System.out.println("Boxed " + boxed + ", unboxed " + unboxed + ", parsed " + parsed);

        // Careful: to compare two Integer objects, use equals(), not ==
        // == checks "is it the same object?", equals() checks "is it the same number?"
        Integer x = 1000;
        Integer y = 1000;
        System.out.println("x == y      : " + (x == y) + "   (outside the -128..127 cache: two different objects)");
        System.out.println("x.equals(y) : " + x.equals(y));

        // Careful: if a wrapper is null, taking the int out of it crashes with NullPointerException
        Integer missing = null;
        // int boom = missing;   // NullPointerException at runtime - there is no number inside to take out
        System.out.println("Null wrapper is safe only while it stays an object: " + missing);
    }
}
