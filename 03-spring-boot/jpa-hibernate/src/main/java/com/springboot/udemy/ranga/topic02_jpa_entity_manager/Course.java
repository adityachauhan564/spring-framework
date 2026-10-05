package com.springboot.udemy.ranga.topic02_jpa_entity_manager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/*
 * The course table as a JPA entity, used by topics 02-04 (topic01 turns rows into objects by hand instead).
 * No @Table / @Column needed: the class name "Course" matches the table "course", and the field
 * names match the column names.
 * The id is chosen by our code (no @GeneratedValue), just like in the course's INSERT statements.
 * Topic01 builds Course objects from rows by itself, with a RowMapper lambda.
 */
@Entity
public class Course {

    @Id
    private long id;
    private String name;
    private String author;

    protected Course() {
        // needed by JPA: it creates an empty object first, then fills the fields
    }

    public Course(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Course[id=" + id + ", name=" + name + ", author=" + author + "]";
    }
}
