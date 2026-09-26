package topic10_data_types_and_operators;

/*
 * Topic    : Operators
 * Key idea : arithmetic, comparison, logical, increment and the ternary operator.
 *            Integer division drops the remainder; && and || stop early (short-circuit).
 * Run      : java -cp out topic10_data_types_and_operators.Operators
 * Try this : predict each line's output before running it.
 */
public class Operators {

    public static void main(String[] args) {
        // arithmetic
        System.out.println("7 / 2   = " + (7 / 2) + "    (int / int = int)");
        System.out.println("7 / 2.0 = " + (7 / 2.0));
        System.out.println("7 % 2   = " + (7 % 2) + "    (remainder: 7 % 2 == 1 means odd)");

        // increment: prefix changes first, postfix uses the old value first
        int count = 5;
        System.out.println("count++ = " + count++ + ", now " + count);
        System.out.println("++count = " + ++count);

        // compound assignment
        int total = 10;
        total += 5;     // total = total + 5
        total *= 2;     // total = total * 2
        System.out.println("total   = " + total);

        // comparison -> boolean
        System.out.println("5 > 3 && 2 > 4 = " + (5 > 3 && 2 > 4));
        System.out.println("5 > 3 || 2 > 4 = " + (5 > 3 || 2 > 4));
        System.out.println("!(5 > 3)       = " + !(5 > 3));

        // short-circuit: the right side is skipped when the answer is already known
        String name = null;
        boolean safe = name != null && name.length() > 3;   // no NullPointerException
        System.out.println("short-circuit safe check = " + safe);

        // ternary: condition ? valueIfTrue : valueIfFalse
        int age = 20;
        String type = age >= 18 ? "adult" : "minor";
        System.out.println("age 20 is an " + type);

        // string concatenation is evaluated left to right
        System.out.println("1 + 2 + \"3\" = " + (1 + 2 + "3"));   // "33"
        System.out.println("\"1\" + 2 + 3 = " + ("1" + 2 + 3));   // "123"
    }
}
