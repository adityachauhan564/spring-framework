package com.springcore.jdbc.topic04_named_params_keys_batch;

/*
 * Topic    : Named parameters, generated keys and batch updates
 * Read     : Course -> CourseRepository -> NamedParametersDemo
 * A record (a short, read-only data class). DataClassRowMapper fills it through its constructor,
 * so no setters are needed.
 * id is Integer, not int, because a course that is not saved yet has no id - it can be null.
 */
public record Course(Integer id, String title, int fee) {

    public static Course unsaved(String title, int fee) {
        return new Course(null, title, fee);
    }
}
