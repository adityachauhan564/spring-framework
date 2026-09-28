package topic03_operators.solutions;

// Solutions for topic03_operators/Exercises.java
public class ExercisesSolution {

    static boolean isEven(int n) {
        return n % 2 == 0;
    }

    static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    static int lastDigit(int n) {
        return n % 10;
    }

    static double average(int a, int b) {
        return (a + b) / 2.0;               // 2.0 makes it double division; (a + b) / 2 would be 3
    }

    static String ageGroup(int age) {
        return age >= 18 ? "adult" : "minor";
    }

    public static void main(String[] args) {
        check(isEven(4) && !isEven(7) && isEven(0), "exercise 1");
        check(isLeapYear(2024) && isLeapYear(2000), "exercise 2");
        check(!isLeapYear(1900) && !isLeapYear(2023), "exercise 2");
        check(lastDigit(1234) == 4 && lastDigit(7) == 7, "exercise 3");
        check(average(3, 4) == 3.5, "exercise 4");
        check(ageGroup(18).equals("adult") && ageGroup(12).equals("minor"), "exercise 5");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
