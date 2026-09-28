package topic04_control_flow;

/*
 * Topic    : Loops: for, while, do-while, break, continue
 * Key idea : use for when you know how many times to repeat, while when you repeat until
 *            something happens, and do-while when the body must run at least once.
 * Run      : java -cp out topic04_control_flow.LoopTypes
 * Try this : change 'i <= 5' to 'i < 5' in the first loop. How many lines print now?
 */
public class LoopTypes {

    public static void main(String[] args) {
        // for: start; condition; step - all in one line
        System.out.print("for 1..5:        ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // while: checks BEFORE each pass, so it may run zero times
        int n = 37;
        int steps = 0;
        while (n > 1) {                               // halve until we reach 1
            n = n / 2;
            steps++;
        }
        System.out.println("while halving 37: " + steps + " steps");

        // do-while: checks AFTER each pass, so the body runs at least once
        int attempts = 0;
        do {
            attempts++;
        } while (attempts < 0);                       // false straight away, yet the body ran once
        System.out.println("do-while ran:     " + attempts + " time(s)");

        // continue: skip the rest of THIS pass; break: leave the loop completely
        System.out.print("odd numbers < 10, stop at 7: ");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue;                             // skip even numbers
            }
            if (i > 7) {
                break;                                // stop the whole loop
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // nested loops: the inner loop runs completely for every pass of the outer loop
        for (int row = 1; row <= 3; row++) {
            System.out.print("row " + row + ": ");
            for (int col = 1; col <= row; col++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }
}
