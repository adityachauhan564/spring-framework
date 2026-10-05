package com.jbdl63.digitallibrary.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * The owning side of User *..* Book. A many-to-many needs a third table (a join table):
 * books_issued has one row for each (user_id, book_id) pair - like the library's issue register.
 * Adding a book to issuedBooks inserts a row. Removing it deletes the row.
 */
@Entity
@Table(name = "library_user")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "issuedBooks")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "User name is required")
    private String userName;

    @NotBlank(message = "Mobile number is required")
    private String userMobileNo;

    @Email(message = "Email is not valid")
    private String userEmailId;

    // Changed only through POST/DELETE /v1/users/{id}/books/{bookId}, never set directly by a client
    @ManyToMany
    @JoinTable(
            name = "books_issued",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    @JsonIgnore
    private List<Book> issuedBooks = new ArrayList<>();
}
