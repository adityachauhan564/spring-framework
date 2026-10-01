package topic04_control_flow;

/*
 * Topic    : Making decisions: if / else-if / else, switch statement, switch expression
 * Key idea : - if / else-if checks the conditions from top to bottom and runs ONLY the
 *              first one that is true. The rest are skipped.
 *              Like a marksheet: 95 marks gets 'A' and stops there, it never reaches 'C'.
 *            - switch looks at ONE value and jumps to the matching case.
 *            - The new switch expression (with ->) also gives back a value, and it never
 *              "falls through" into the next case by mistake.
 * Run      : java -cp out topic04_control_flow.GradeCalculator
 * Try this : Move the "score >= 60" check above "score >= 90" and see what goes wrong.
 */
public class GradeCalculator {

    // if / else-if: the order is important - the first true condition wins
    static char grade(int score) {
        if (score < 0 || score > 100) {
            return '?';                                 // marks below 0 or above 100 are impossible, so reject them first
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

    // old-style switch: every case needs a break.
    // If you forget break, Java keeps running into the next case also (this is called "fall-through").
    static String describeOld(char grade) {
        String text;
        switch (grade) {
            case 'A':
                text = "excellent";
                break;
            case 'B':
            case 'C':                                   // B has no code, so it falls into C on purpose - both share one answer
                text = "passed";
                break;
            default:                                    // default = "none of the above"
                text = "try again";
        }
        return text;
    }

    // new switch expression (Java 14+): uses arrows ->, many values per case, gives back a value, no break needed
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
