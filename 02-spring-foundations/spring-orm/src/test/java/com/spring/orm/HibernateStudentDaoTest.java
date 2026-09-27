package com.spring.orm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.spring.orm.topic01_entity_mapping.Student;
import com.spring.orm.topic02_session_factory_config.HibernateConfig;
import com.spring.orm.topic03_hibernate_crud_and_hql.StudentDao;

/* DB_URL blanked: always the in-memory H2 database. */
@SpringJUnitConfig({HibernateConfig.class, StudentDao.class})
@TestPropertySource(properties = "DB_URL=")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class HibernateStudentDaoTest {

    @Autowired
    StudentDao dao;

    @Test
    void saveAssignsAGeneratedId() {
        assertNotNull(dao.save(new Student("Asha", "Pune")).getStudentId());
    }

    @Test
    void hqlFindsByCity() {
        dao.save(new Student("Asha", "Pune"));
        dao.save(new Student("Ravi", "Delhi"));
        assertEquals(1, dao.findByCity("Pune").size());
    }

    @Test
    void changeIsSavedByDirtyCheckingWithoutUpdate() {
        int id = dao.save(new Student("Asha", "Pune")).getStudentId();
        assertTrue(dao.changeCity(id, "Mumbai"));
        assertEquals("Mumbai", dao.findById(id).orElseThrow().getStudentCity());
    }

    @Test
    void deleteRemovesTheStudent() {
        int id = dao.save(new Student("Asha", "Pune")).getStudentId();
        assertTrue(dao.delete(id));
        assertTrue(dao.findById(id).isEmpty());
        assertFalse(dao.delete(id));
    }
}
