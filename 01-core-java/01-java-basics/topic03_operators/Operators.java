package topic03_operators;

/*
 * Topic    : Operators
 * Key idea : Operators are symbols that do work on values:
 *              maths:       + - * / %
 *              comparison:  > < >= <= == !=   (the answer is true or false)
 *              logical:     && (AND)  || (OR)  ! (NOT)
 *              increment:   ++ and --
 *              ternary:     condition ? valueIfYes : valueIfNo
 *            Two things to remember:
 *              - int / int gives an int. 7 / 2 is 3, the .5 is thrown away.
 *              - && and || stop early once the answer is already known (this is called short-circuit).
 * Run      : java -cp out topic03_operators.Operators
 * Try this : Guess the output of each line BEFORE running it.
 */
public class Operators {

    public static void main(String[] args) {
        // maths
        System.out.println("7 / 2   = " + (7 / 2) + "    (int / int = int)");
        System.out.println("7 / 2.0 = " + (7 / 2.0));
        System.out.println("7 % 2   = " + (7 % 2) + "    (remainder: 7 % 2 == 1 means odd)");

        // increment:
        //   count++ (postfix) = use the OLD value first, then add 1
        //   ++count (prefix)  = add 1 first, then use the NEW value
        int count = 5;
        System.out.println("count++ = " + count++ + ", now " + count);
        System.out.println("++count = " + ++count);

        // short forms: total += 5 is the same as total = total + 5
        int total = 10;
        total += 5;     // total = total + 5   -> 15
        total *= 2;     // total = total * 2   -> 30
        System.out.println("total   = " + total);

        // comparison and logical operators always give true or false
        System.out.println("5 > 3 && 2 > 4 = " + (5 > 3 && 2 > 4));    // AND: both sides must be true
        System.out.println("5 > 3 || 2 > 4 = " + (5 > 3 || 2 > 4));    // OR: any one side true is enough
        System.out.println("!(5 > 3)       = " + !(5 > 3));            // NOT: flips true to false

        // short-circuit: if the left side already decides the answer, the right side is never checked
        // here name is null, so "name != null" is false and name.length() is skipped - no crash
        String name = null;
        boolean safe = name != null && name.length() > 3;   // no NullPointerException
        System.out.println("short-circuit safe check = " + safe);

        // ternary: a one-line if/else.  condition ? valueIfYes : valueIfNo
        int age = 20;
        String type = age >= 18 ? "adult" : "minor";       // 18 or more can vote -> "adult"
        System.out.println("age 20 is an " + type);

        // + with text works from left to right:
        //   numbers first are added as maths, once text comes in, everything after is joined as text
        System.out.println("1 + 2 + \"3\" = " + (1 + 2 + "3"));   // 1 + 2 = 3, then "3" + "3" = "33"
        System.out.println("\"1\" + 2 + 3 = " + ("1" + 2 + 3));   // text first, so "1" + "2" + "3" = "123"
    }
}
