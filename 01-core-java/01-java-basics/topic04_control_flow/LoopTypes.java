package topic04_control_flow;

/*
 * Topic    : Loops: for, while, do-while, break, continue
 * Key idea : Which loop to pick?
 *              for      - you know how many times to repeat. "Do 10 push-ups."
 *              while    - repeat until something happens. "Keep stirring the chai until it boils."
 *              do-while - the body must run at least once. "Taste the dal first, then decide
 *                         if it needs more salt."
 * Run      : java -cp out topic04_control_flow.LoopTypes
 * Try this : Change 'i <= 5' to 'i < 5' in the first loop. How many numbers print now?
 */
public class LoopTypes {

    public static void main(String[] args) {
        // for: start; condition; step - all three written in one line
        System.out.print("for 1..5:        ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // while: checks the condition BEFORE every round, so it may run zero times
        int n = 37;
        int steps = 0;
        while (n > 1) {                               // keep cutting n in half until it becomes 1
            n = n / 2;
            steps++;
        }
        System.out.println("while halving 37: " + steps + " steps");

        // do-while: checks the condition AFTER every round, so the body always runs at least once
        int attempts = 0;
        do {
            attempts++;
        } while (attempts < 0);                       // this is false from the start, but the body already ran once
        System.out.println("do-while ran:     " + attempts + " time(s)");

        // continue = skip the rest of THIS round and go to the next round
        // break    = stop the whole loop and come out
        System.out.print("odd numbers < 10, stop at 7: ");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue;                             // even number - skip it
            }
            if (i > 7) {
                break;                                // crossed 7 - stop the loop completely
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // nested loops: a loop inside a loop.
        // For every ONE round of the outer loop, the inner loop runs fully.
        // Like a clock: for every 1 hour, the minute hand goes round all 60 minutes.
        for (int row = 1; row <= 3; row++) {
            System.out.print("row " + row + ": ");
            for (int col = 1; col <= row; col++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }
}
