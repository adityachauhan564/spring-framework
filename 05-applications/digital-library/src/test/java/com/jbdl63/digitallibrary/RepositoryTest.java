package com.jbdl63.digitallibrary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jbdl63.digitallibrary.model.Book;
import com.jbdl63.digitallibrary.model.User;
import com.jbdl63.digitallibrary.repository.BookRepository;
import com.jbdl63.digitallibrary.repository.UserRepository;

import jakarta.persistence.EntityManager;

/*
 * @DataJpaTest starts only the JPA part (entities, repositories, H2, data.sql) and rolls every
 * test back afterwards. It checks the derived queries and the relationship mapping against real SQL.
 */
@DataJpaTest
class RepositoryTest {

    @Autowired BookRepository bookRepository;
    @Autowired UserRepository userRepository;
    @Autowired EntityManager entityManager;
    @Autowired JdbcTemplate jdbc;

    @Test
    void derivedQueryFollowsTheAuthorRelationship() {
        assertThat(bookRepository.findByAuthorAuthorName("J.K. Rowling")).hasSize(2);
        assertThat(bookRepository.findByAuthorAuthorName("nobody")).isEmpty();
    }

    @Test
    void categorySearchIgnoresCase() {
        assertThat(bookRepository.findByBookCategoryIgnoreCase("fantasy"))
                .extracting(Book::getBookName)
                .allMatch(name -> name.startsWith("Harry Potter"));
    }

    @Test
    void issuingABookWritesARowInTheJoinTable() {
        User user = userRepository.findById(1).orElseThrow();
        user.getIssuedBooks().add(bookRepository.findById(3).orElseThrow());
        entityManager.flush();                        // send the pending SQL now, not at commit

        Integer rows = jdbc.queryForObject("select count(*) from books_issued where user_id = 1 and book_id = 3", Integer.class);
        assertThat(rows).isEqualTo(1);

        entityManager.clear();                        // forget loaded objects: the next read comes from the database
        assertThat(userRepository.findById(1).orElseThrow().getIssuedBooks()).extracting(Book::getBookId).containsExactly(3);
    }
}
