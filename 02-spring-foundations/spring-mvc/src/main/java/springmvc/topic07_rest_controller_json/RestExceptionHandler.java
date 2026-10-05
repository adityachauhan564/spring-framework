package springmvc.topic07_rest_controller_json;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import springmvc.topic06_exception_handling.UserNotFoundException;

/*
 * The JSON twin of topic06's PageExceptionHandler.
 *   basePackageClasses - works only for controllers in THIS package (the API)
 *   @Order(highest)    - checked before the page handler, which would otherwise answer with an HTML page
 * ProblemDetail (RFC 9457) is the standard JSON format for errors, so every client knows how to read it:
 *   {"type":"about:blank","title":"Not Found","status":404,"detail":"No user with id 999"}
 */
@RestControllerAdvice(basePackageClasses = UserRestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail notFound(UserNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail invalid(MethodArgumentNotValidException e) {
        Map<String, String> fieldErrors = new TreeMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "validation failed");
        problem.setProperty("fieldErrors", fieldErrors);
        return problem;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ProblemDetail status(ResponseStatusException e) {
        return ProblemDetail.forStatusAndDetail(e.getStatusCode(), e.getReason());
    }
}
