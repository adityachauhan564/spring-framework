package topic04_control_flow;

/*
 * Topic    : Making decisions: if / else-if / else, switch statement, switch expression
 * Key idea : if/else-if checks conditions top to bottom and runs the FIRST branch that matches.
 *            switch picks a branch by matching ONE value; the modern switch expression also
 *            returns a value and never "falls through".
 * Run      : java -cp out topic04_control_flow.GradeCalculator
 * Try this : move the "score >= 60" branch above "score >= 90" and see what goes wrong.
 */
public class GradeCalculator {

    // if / else-if: order matters - the first true condition wins
    static char grade(int score) {
        if (score < 0 || score > 100) {
            return '?';                                 // reject impossible input first
        } else if (score >= 90) {
            return 'A';
        } else if (score >= 75) {
            return 'B';
        } else if (score >= 60) {
            return 'C';
        } else {
            return 'F';
        }
    }

    // classic switch statement: every case needs a break, or execution falls into the next case
    static String describeOld(char grade) {
        String text;
        switch (grade) {
            case 'A':
                text = "excellent";
                break;
            case 'B':
            case 'C':                                   // two cases sharing one body (deliberate fall-through)
                text = "passed";
                break;
            default:
                text = "try again";
        }
        return text;
    }

    // switch expression (Java 14+): arrows, several labels per case, returns a value, no break
    static String describe(char grade) {
        return switch (grade) {
            case 'A' -> "excellent";
            case 'B', 'C' -> "passed";
            default -> "try again";
        };
    }

    public static void main(String[] args) {
        int[] scores = {95, 80, 61, 42, 120};
        for (int score : scores) {
            char g = grade(score);
            System.out.println(score + " -> " + g + " (" + describe(g) + ")");
        }
        System.out.println("old-style switch gives the same answer: " + describeOld('B'));
    }
}
