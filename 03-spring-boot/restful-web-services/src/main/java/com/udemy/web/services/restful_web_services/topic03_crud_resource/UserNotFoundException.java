package com.udemy.web.services.restful_web_services.topic03_crud_resource;

/*
 * Thrown when an id does not exist. Topic04's GlobalExceptionHandler turns it into a
 * 404 response with a JSON body. The controller never builds error responses itself.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int id) {
        super("No user with id " + id);
    }
}
