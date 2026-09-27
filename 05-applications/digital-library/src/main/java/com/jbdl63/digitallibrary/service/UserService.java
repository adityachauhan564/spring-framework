package com.jbdl63.digitallibrary.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jbdl63.digitallibrary.exception.ConflictException;
import com.jbdl63.digitallibrary.exception.DataNotFoundException;
import com.jbdl63.digitallibrary.model.Book;
import com.jbdl63.digitallibrary.model.User;
import com.jbdl63.digitallibrary.repository.BookRepository;
import com.jbdl63.digitallibrary.repository.UserRepository;

/*
 * Issuing a book = adding it to user.issuedBooks. User owns the many-to-many, so when the transaction
 * commits Hibernate turns that list change into an insert (or delete) in the books_issued join table.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public User addNewUser(User user) {
        user.setUserId(null);
        return userRepository.save(user);
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found: " + userId));
    }

    @Transactional
    public User updateUser(Integer userId, User changes) {
        User user = findById(userId);
        user.setUserName(changes.getUserName());
        user.setUserMobileNo(changes.getUserMobileNo());
        user.setUserEmailId(changes.getUserEmailId());
        return user;
    }

    @Transactional
    public void deleteUser(Integer userId) {
        User user = findById(userId);
        if (!user.getIssuedBooks().isEmpty()) {
            throw new ConflictException("User still has " + user.getIssuedBooks().size() + " book(s) to return");
        }
        userRepository.delete(user);
    }

    // issuedBooks is LAZY: it must be read while the transaction is still open, hence the copy
    @Transactional(readOnly = true)
    public List<Book> findAllBooksIssuedToUser(Integer userId) {
        return List.copyOf(findById(userId).getIssuedBooks());
    }

    @Transactional
    public List<Book> issueBook(Integer userId, Integer bookId) {
        User user = findById(userId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new DataNotFoundException("Book not found: " + bookId));
        if (user.getIssuedBooks().contains(book)) {
            throw new ConflictException("Book " + bookId + " is already issued to user " + userId);
        }
        user.getIssuedBooks().add(book);
        return List.copyOf(user.getIssuedBooks());
    }

    @Transactional
    public List<Book> returnBook(Integer userId, Integer bookId) {
        User user = findById(userId);
        boolean removed = user.getIssuedBooks().removeIf(book -> book.getBookId().equals(bookId));
        if (!removed) {
            throw new DataNotFoundException("Book " + bookId + " is not issued to user " + userId);
        }
        return List.copyOf(user.getIssuedBooks());
    }
}
