package com.springcore.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.springcore.jdbc.topic01_datasource_and_jdbctemplate.JdbcConfig;
import com.springcore.jdbc.topic02_crud_dao.Student;
import com.springcore.jdbc.topic02_crud_dao.StudentDao;
import com.springcore.jdbc.topic02_crud_dao.StudentDaoImpl;

/* DB_URL is set to empty here, so tests always use the in-memory H2 database - never a real MySQL database. */
@SpringJUnitConfig({JdbcConfig.class, StudentDaoImpl.class})
@TestPropertySource(properties = "DB_URL=")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)   // a fresh, empty database for every test
class StudentDaoTest {

    @Autowired
    StudentDao dao;

    @Test
    void insertThenFindById() {
        dao.insert(new Student(1, "Asha", "Pune"));
        assertEquals("Asha", dao.findById(1).orElseThrow().getName());
    }

    @Test
    void updateChangesTheRow() {
        dao.insert(new Student(1, "Asha", "Pune"));
        assertEquals(1, dao.update(new Student(1, "Asha", "Delhi")));
        assertEquals("Delhi", dao.findById(1).orElseThrow().getCity());
    }

    @Test
    void deleteRemovesTheRowAndFindReturnsEmpty() {
        dao.insert(new Student(1, "Asha", "Pune"));
        assertEquals(1, dao.delete(1));
        assertTrue(dao.findById(1).isEmpty());
    }

    @Test
    void findAllIsOrderedById() {
        dao.insert(new Student(2, "Ravi", "Delhi"));
        dao.insert(new Student(1, "Asha", "Pune"));
        assertEquals(1, dao.findAll().get(0).getId());
    }
}
