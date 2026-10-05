package com.api.book.rest_first.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * ENTITY layer: one row of the books table.
 * GenerationType.IDENTITY = the database's auto-increment column (1, 2, 3... by itself).
 * The old AUTO strategy made Hibernate create and use a hidden sequence table on MySQL -
 * surprising, and slower.
 * The entity never leaves the service layer. The API sends a BookResponse instead (dto package).
 */
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_title", nullable = false)      // Java field "title" -> column "book_title"
    private String title;

    @Column(nullable = false)
    private String author;

    protected Book() {
        // needed by JPA: it creates an empty object first, then fills the fields
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void update(String newTitle, String newAuthor) {
        this.title = newTitle;
        this.author = newAuthor;
    }

    @Override
    public String toString() {
        return "Book[id=" + id + ", title=" + title + ", author=" + author + "]";
    }
}
