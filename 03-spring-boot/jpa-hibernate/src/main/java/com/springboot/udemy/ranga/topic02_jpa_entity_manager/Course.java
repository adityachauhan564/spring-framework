package com.springboot.udemy.ranga.topic02_jpa_entity_manager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/*
 * The course table as a JPA entity, used by topics 02-04 (topic01 maps rows by hand instead).
 * No @Table / @Column needed: the class name "Course" matches table "course", and the field
 * names match the column names. The id is chosen by the application (no @GeneratedValue),
 * like the course's INSERT statements.
 * Topic01 builds Course objects from rows itself, with a RowMapper lambda.
 */
@Entity
public class Course {

    @Id
    private long id;
    private String name;
    private String author;

    protected Course() {
        // required by JPA
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
