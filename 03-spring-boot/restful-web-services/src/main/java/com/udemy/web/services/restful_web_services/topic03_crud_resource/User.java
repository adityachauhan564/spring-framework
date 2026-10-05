package com.udemy.web.services.restful_web_services.topic03_crud_resource;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

/*
 * The resource. It is a record, so it is immutable (cannot be changed after creation).
 * That is why the store REPLACES a user instead of editing it.
 * The validation annotations are used by topic04 (@Valid in UserResource).
 * id is Integer, not int: a user sent in a POST body has no id yet, so it is null.
 */
public record User(
        Integer id,
        @Size(min = 2, max = 50, message = "name must be 2 to 50 characters") String name,
        @NotNull(message = "birthDate is required") @Past(message = "birthDate must be in the past") LocalDate birthDate) {

    public User withId(int newId) {
        return new User(newId, name, birthDate);
    }
}
