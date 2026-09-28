package topic10_exception_basics;

/*
 * Topic    : throw, throws and finally
 * Key idea : 'throw' raises an exception yourself when an argument is wrong, so the error
 *            appears where the mistake is. 'throws' in a method signature warns callers about a
 *            checked exception. 'finally' runs whether or not an exception happened.
 * Run      : java -cp out topic10_exception_basics.ThrowAndFinally
 * Try this : remove 'throws Exception' from riskyCheck - the file stops compiling. Why?
 */
public class ThrowAndFinally {

    // Reject bad input at once, with a clear message, instead of returning a wrong answer
    static int percent(int part, int total) {
        if (total <= 0) {
            throw new IllegalArgumentException("total must be positive, got " + total);
        }
        return part * 100 / total;
    }

    // A checked exception (Exception, not RuntimeException) must be declared with 'throws'
    static void riskyCheck(boolean fail) throws Exception {
        if (fail) {
            throw new Exception("the check failed");
        }
    }

    public static void main(String[] args) {
        System.out.println("percent(1, 4) = " + percent(1, 4));

        try {
            percent(1, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        for (boolean fail : new boolean[] {false, true}) {
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
