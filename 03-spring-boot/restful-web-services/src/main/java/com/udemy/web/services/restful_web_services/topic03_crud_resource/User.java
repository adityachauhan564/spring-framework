package com.udemy.web.services.restful_web_services.topic03_crud_resource;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

/*
 * The resource. A record: immutable, so the store replaces users instead of editing them.
 * The validation annotations are used by topic04 (@Valid in UserResource).
 * id is Integer, not int: a user sent in a POST body has no id yet (null).
 */
public record User(
        Integer id,
        @Size(min = 2, max = 50, message = "name must be 2 to 50 characters") String name,
        @NotNull(message = "birthDate is required") @Past(message = "birthDate must be in the past") LocalDate birthDate) {

    public User withId(int newId) {
        return new User(newId, name, birthDate);
    }
}
