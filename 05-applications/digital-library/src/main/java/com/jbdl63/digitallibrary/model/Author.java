package com.jbdl63.digitallibrary.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * One author writes many books: this is the "one" side of a one-to-many.
 * mappedBy = "author" says Book.author owns the relationship (the author_id column is in
 * library_book). So this list is only another view of the same foreign key.
 * Like a teacher and students: the link is written in each student's record ("class teacher: X").
 */
@Entity
@Table(name = "library_author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "booksList")   // Book.toString prints its author. Printing the books here too would go round and round forever
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Author Name should not be blank")
    private String authorName;

    private String authorAddress;

    // Deleting an author deletes their books too (CascadeType.ALL).
    // Not in the JSON: a book already shows its author, and author -> books -> author -> ... would never end.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    @JsonIgnore
    @Builder.Default                 // without it, the builder ignores "= new ArrayList<>()" and leaves the list null
    private List<Book> booksList = new ArrayList<>();
}
