package springmvc.topic06_exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * @ControllerAdvice = shared code for many controllers. Its @ExceptionHandler methods
 * catch exceptions thrown by ANY controller method, and decide what to send back instead.
 * Like a bank's complaint desk: every counter sends its problems to one desk.
 *   - a known problem (UserNotFoundException) -> 404, with a helpful message
 *   - anything else                          -> 500, with a general message. Never show
 *     stack traces or internal details to users (write them to the log instead)
 * The JSON API (topic07) has its own advice that returns JSON, so this one returns pages.
 */
@ControllerAdvice
public class PageExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFound(UserNotFoundException e, Model model) {
        model.addAttribute("status", 404);
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String unexpected(Exception e, Model model) throws Exception {
        if (e.getClass().getName().startsWith("org.springframework.")) {
            // Spring's own request errors (wrong parameter type, unknown URL, wrong HTTP method,
            // broken JSON...) must keep their proper 400/404/405 status. Throwing them again
            // gives them back to Spring's default handling, instead of turning them into a 500.
            throw e;
        }
        System.err.println("Unexpected error: " + e);      // a real app would use a logger here
        model.addAttribute("status", 500);
        model.addAttribute("message", "Sorry, something went wrong. Please try again later.");
        return "error";
    }
}
