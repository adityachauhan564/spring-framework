package springmvc.topic06_exception_handling;

/*
 * Topic    : Handling errors in one place
 * Read     : UserNotFoundException -> UserPageController -> PageExceptionHandler -> error.jsp
 * A domain exception: says WHAT went wrong, not how to show it. Unchecked, so
 * controllers don't need throws clauses.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int id) {
        super("No user with id " + id);
    }
}
