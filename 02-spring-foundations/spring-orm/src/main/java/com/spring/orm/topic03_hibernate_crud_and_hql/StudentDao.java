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
 * Key idea : - Inject the SessionFactory. Inside a @Transactional method,
 *              getCurrentSession() gives you the Session that belongs to that transaction.
 *              (A Session = one conversation with the database.)
 *            - No SQL for CRUD - Hibernate writes it from the @Entity mapping.
 *            - HQL (Hibernate Query Language) looks like SQL, but uses CLASS and FIELD names
 *              (Student, studentCity), not table and column names.
 * Compare  : spring-jdbc topic02 does the same CRUD with hand-written SQL.
 */
@Repository
@Transactional                      // every public method runs inside a transaction
public class StudentDao {

    private final SessionFactory sessionFactory;

    public StudentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public Student save(Student student) {
        session().persist(student);         // INSERT. After this, the new id is already set on the object
        return student;
    }

    @Transactional(readOnly = true)         // a hint: "this only reads", so Hibernate can skip checking for changes
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
        student.setStudentCity(newCity);    // no update() call needed: Hibernate saves the change by itself at commit
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
