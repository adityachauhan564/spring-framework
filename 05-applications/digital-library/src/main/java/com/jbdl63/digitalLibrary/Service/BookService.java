package com.jbdl63.digitalLibrary.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbdl63.digitalLibrary.Exceptions.BadRequestException;
import com.jbdl63.digitalLibrary.Exceptions.DataNotFoundException;
import com.jbdl63.digitalLibrary.Model.Author;
import com.jbdl63.digitalLibrary.Model.Book;
import com.jbdl63.digitalLibrary.Repository.AuthorRepository;
import com.jbdl63.digitalLibrary.Repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book addNewBook(Book book) {
        if (book.getAuthor() == null || book.getAuthor().getAuthorId() == null) {
            throw new BadRequestException("Book must have author.authorId");
        }
        Author author = authorRepository.findById(book.getAuthor().getAuthorId())
                .orElseThrow(() -> new DataNotFoundException("Author not found: " + book.getAuthor().getAuthorId()));
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public void deleteBookById(Integer bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<Book> findBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthorAuthorName(authorName);
    }
}