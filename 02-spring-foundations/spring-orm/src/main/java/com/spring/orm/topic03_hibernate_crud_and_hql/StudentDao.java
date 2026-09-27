package com.spring.orm.topic03_hibernate_crud_and_hql;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.orm.topic01_entity_mapping.Student;

/*
 * Topic    : CRUD and HQL with the SessionFactory - the modern native-Hibernate way
 * Key idea : inject the SessionFactory; inside a @Transactional method,
 *            getCurrentSession() returns the Session bound to that transaction.
 *            No SQL for CRUD - Hibernate generates it from the @Entity mapping.
 *            HQL queries use CLASS and FIELD names (Student, studentCity), not table/column names.
 * Compare  : spring-jdbc topic02 does the same CRUD with hand-written SQL.
 */
@Repository
@Transactional                      // every public method runs in a transaction
public class StudentDao {

    private final SessionFactory sessionFactory;

    public StudentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public Student save(Student student) {
        session().persist(student);         // INSERT; the generated id is set on the object
        return student;
    }

    @Transactional(readOnly = true)         // a hint: no changes expected, Hibernate can skip dirty checks
    public Optional<Student> findById(int id) {
        return Optional.ofNullable(session().get(Student.class, id));
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return session().createQuery("from Student s order by s.studentId", Student.class).list();
    }

    @Transactional(readOnly = true)
    public List<Student> findByCity(String city) {
        return session().createQuery("from Student s where s.studentCity = :city", Student.class)
                .setParameter("city", city)
                .list();
    }

    public boolean changeCity(int id, String newCity) {
        Student student = session().get(Student.class, id);
        if (student == null) {
            return false;
        }
        student.setStudentCity(newCity);    // no update() call: Hibernate saves the change at commit
        return true;
    }

    public boolean delete(int id) {
        Student student = session().get(Student.class, id);
        if (student == null) {
            return false;
        }
        session().remove(student);          // DELETE
        return true;
    }
}
