package com.jbdl63.digitallibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbdl63.digitallibrary.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // "AuthorAuthorName" follows a relationship: book.author.authorName (a join, written for you).
    // The same query by hand:  @Query("select b from Book b where b.author.authorName = ?1")
    List<Book> findByAuthorAuthorName(String authorName);

    List<Book> findByBookCategoryIgnoreCase(String bookCategory);
}
