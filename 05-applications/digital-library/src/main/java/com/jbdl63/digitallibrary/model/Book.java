package com.jbdl63.digitallibrary.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * The "many" side of Author 1..* Book. It OWNS the relationship, so the author_id foreign key is here.
 * Book *..* User is a many-to-many. User owns that one (see User.issuedBooks); this side is mappedBy.
 */
@Entity
@Table(name = "library_book")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "users")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @NotBlank(message = "Book name is required")
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String bookName;

    @NotBlank(message = "Publication year is required")
    @Column(nullable = false)
    private String publicationYear;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be more than 0")
    @Column(nullable = false)
    private Double bookPrice;

    @NotBlank(message = "Edition is required")
    @Size(max = 10)
    @Column(nullable = false, length = 10)
    private String bookEdition;

    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    private String bookCategory;

    // In a request, only {"authorId": 1} is needed. BookService loads the real author.
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    // Who has this book right now. Hidden in the JSON: GET /v1/users/{id}/books shows it from the other side.
    @ManyToMany(mappedBy = "issuedBooks")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    // Set by Hibernate, never by a client. READ_ONLY means they are ignored in request bodies
    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationTime;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updationTime;
}
