package com.jbdl63.digitallibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jbdl63.digitallibrary.exception.BadRequestException;
import com.jbdl63.digitallibrary.exception.DataNotFoundException;
import com.jbdl63.digitallibrary.model.Author;
import com.jbdl63.digitallibrary.model.Book;
import com.jbdl63.digitallibrary.repository.AuthorRepository;
import com.jbdl63.digitallibrary.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Book addNewBook(Book book) {
        book.setBookId(null);
        book.setAuthor(existingAuthor(book));
        return bookRepository.save(book);
    }

    public Book findById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new DataNotFoundException("Book not found: " + bookId));
    }

    @Transactional
    public Book updateBook(Integer bookId, Book changes) {
        Book book = findById(bookId);
        book.setBookName(changes.getBookName());
        book.setPublicationYear(changes.getPublicationYear());
        book.setBookPrice(changes.getBookPrice());
        book.setBookEdition(changes.getBookEdition());
        book.setBookCategory(changes.getBookCategory());
        book.setAuthor(existingAuthor(changes));
        return book;
    }

    // An issued book still has a row in books_issued, so the delete fails with a database rule error -> 409
    public void deleteBookById(Integer bookId) {
        bookRepository.delete(findById(bookId));
    }

    public List<Book> findBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthorAuthorName(authorName);
    }

    public List<Book> findBooksByCategory(String category) {
        return bookRepository.findByBookCategoryIgnoreCase(category);
    }

    private Author existingAuthor(Book book) {
        if (book.getAuthor() == null || book.getAuthor().getAuthorId() == null) {
            throw new BadRequestException("Book must have author.authorId");
        }
        Integer authorId = book.getAuthor().getAuthorId();
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new DataNotFoundException("Author not found: " + authorId));
    }
}
