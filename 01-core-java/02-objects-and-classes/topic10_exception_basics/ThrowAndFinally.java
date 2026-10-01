package topic10_exception_basics;

/*
 * Topic    : throw, throws and finally - three words that look alike but do different jobs
 * Key idea : - throw   = YOU raise an exception when you get wrong input.
 *                        The error shows up right where the mistake is, not 10 steps later.
 *            - throws  = written in the method's first line. A warning to the caller:
 *                        "this method can give you a checked exception, be ready".
 *            - finally = a block that ALWAYS runs, error or no error.
 *                        Like switching off the gas after cooking - whether the food came out good or not.
 * Run      : java -cp out topic10_exception_basics.ThrowAndFinally
 * Try this : Remove 'throws Exception' from riskyCheck - the file stops compiling. Why?
 */
public class ThrowAndFinally {

    // Stop wrong input right away with a clear message, instead of quietly giving a wrong answer
    static int percent(int part, int total) {
        if (total <= 0) {
            throw new IllegalArgumentException("total must be positive, got " + total);
        }
        return part * 100 / total;
    }

    // Exception (not RuntimeException) is a checked exception, so the method must say 'throws'
    static void riskyCheck(boolean fail) throws Exception {
        if (fail) {
            throw new Exception("the check failed");
        }
    }

    public static void main(String[] args) {
        System.out.println("percent(1, 4) = " + percent(1, 4));

        try {
            percent(1, 0);                        // total is 0, so percent() throws
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        for (boolean fail : new boolean[] {false, true}) {     // run once without error, once with error
            try {
                riskyCheck(fail);
                System.out.println("riskyCheck(" + fail + ") passed");
            } catch (Exception e) {
                System.out.println("riskyCheck(" + fail + ") threw: " + e.getMessage());
            } finally {
                System.out.println("  finally runs either way (use it to clean up)");
            }
        }
    }
}
