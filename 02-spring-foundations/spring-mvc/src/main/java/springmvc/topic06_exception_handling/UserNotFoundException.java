package springmvc.topic06_exception_handling;

/*
 * Topic    : Handling errors in one place
 * Read     : UserNotFoundException -> UserPageController -> PageExceptionHandler -> error.jsp
 * A domain exception: it says WHAT went wrong, not how to show it.
 * It is unchecked (extends RuntimeException), so controllers don't need "throws" clauses.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int id) {
        super("No user with id " + id);
    }
}
