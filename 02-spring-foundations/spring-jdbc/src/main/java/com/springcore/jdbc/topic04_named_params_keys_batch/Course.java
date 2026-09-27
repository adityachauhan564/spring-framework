package com.springcore.jdbc.topic04_named_params_keys_batch;

/*
 * Topic    : Named parameters, generated keys and batch updates
 * Read     : Course -> CourseRepository -> NamedParametersDemo
 * A record: DataClassRowMapper maps columns onto its constructor (no setters needed).
 * id is Integer, not int, so a course that isn't saved yet can have id == null.
 */
public record Course(Integer id, String title, int fee) {

    public static Course unsaved(String title, int fee) {
        return new Course(null, title, fee);
    }
}
