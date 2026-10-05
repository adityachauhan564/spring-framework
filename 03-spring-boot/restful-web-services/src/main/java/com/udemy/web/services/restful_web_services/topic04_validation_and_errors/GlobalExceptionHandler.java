package com.udemy.web.services.restful_web_services.topic04_validation_and_errors;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.udemy.web.services.restful_web_services.topic03_crud_resource.UserNotFoundException;

/*
 * Topic    : Validation and consistent error responses
 * Key idea : 1. @Valid on a @RequestBody checks the rules written on User (@Size, @Past...).
 *               If a rule is broken, MethodArgumentNotValidException is thrown BEFORE the method runs.
 *               Like a security guard checking your ID before you enter the office.
 *            2. ONE @RestControllerAdvice turns every error into the same JSON shape:
 *               ProblemDetail (RFC 9457) - {"type","title","status","detail","instance",...}.
 *            Extending ResponseEntityExceptionHandler gives the same ProblemDetail bodies for
 *            Spring MVC's own errors too (bad JSON, wrong method, missing parameter...).
 * Try this : curl -i -u admin:admin123 -X POST localhost:8080/users -H "Content-Type: application/json" -d "{\"name\":\"A\",\"birthDate\":\"2999-01-01\"}"
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail userNotFound(UserNotFoundException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problem.setTitle("User not found");
        return problem;
    }

    // replaces the built-in handling of @Valid failures, so that every field error is listed
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> fieldErrors = new TreeMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "The request body has invalid fields");
        problem.setTitle("Validation failed");
        problem.setProperty("fieldErrors", fieldErrors);
        return ResponseEntity.badRequest().body(problem);
    }
}
