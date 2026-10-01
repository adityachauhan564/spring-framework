package topic04_control_flow.solutions;

// Answers for topic04_control_flow/Exercises.java
public class ExercisesSolution {

    static String fizzBuzz(int n) {
        if (n % 15 == 0) {                  // check the "both" case first, otherwise 15 would stop at "Fizz"
            return "FizzBuzz";
        } else if (n % 3 == 0) {
            return "Fizz";
        } else if (n % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(n);           // turns the number into text: 7 -> "7"
    }

    static String timesTable(int n) {
        String line = "";
        for (int i = 1; i <= 5; i++) {
            line += n * i;
            if (i < 5) {
                line += " ";                // a space between numbers, but not after the last one
            }
        }
        return line;
    }

    static int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;                  // take the last digit (1234 % 10 = 4)
            n /= 10;                        // remove the last digit (1234 / 10 = 123)
        }
        return sum;
    }

    static int daysInMonth(int month) {
        return switch (month) {
            case 2 -> 28;
            case 4, 6, 9, 11 -> 30;
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            default -> 0;                   // any other number is not a real month
        };
    }

    public static void main(String[] args) {
        check(fizzBuzz(15).equals("FizzBuzz") && fizzBuzz(9).equals("Fizz"), "exercise 1");
        check(fizzBuzz(10).equals("Buzz") && fizzBuzz(7).equals("7"), "exercise 1");
        check(timesTable(3).equals("3 6 9 12 15"), "exercise 2");
        check(timesTable(1).equals("1 2 3 4 5"), "exercise 2");
        check(digitSum(1234) == 10 && digitSum(7) == 7, "exercise 3");
        check(daysInMonth(2) == 28 && daysInMonth(4) == 30 && daysInMonth(12) == 31, "exercise 4");
        check(daysInMonth(13) == 0, "exercise 4");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
