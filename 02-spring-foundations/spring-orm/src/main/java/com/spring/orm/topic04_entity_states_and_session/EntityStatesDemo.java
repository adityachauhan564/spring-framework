package com.spring.orm.topic04_entity_states_and_session;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.orm.topic01_entity_mapping.Student;
import com.spring.orm.topic02_session_factory_config.HibernateConfig;

/*
 * Topic    : Entity states and the Session - how Hibernate really behaves
 * Key idea : an object is in one of these states:
 *              transient  - new Student(...): Hibernate doesn't know it
 *              persistent - attached to an open Session: changes are saved AUTOMATICALLY at commit
 *                           (dirty checking), and get() returns the same object (first-level cache)
 *              detached   - its Session closed: changes are NOT saved until you merge() it
 *            Knowing this explains "why did my change save without update()?" and
 *            "why didn't my change save?" - the two most common Hibernate surprises.
 * Run      : ./mvnw -q -pl spring-orm compile exec:java -Dexec.mainClass=com.spring.orm.topic04_entity_states_and_session.EntityStatesDemo
 * Try this : call session.detach(student) before the setter in step 2 - is the change still saved?
 *
 * (Written with SessionFactory.inTransaction(...) instead of @Transactional, so each
 *  Session's start and end is visible right here in the code.)
 * The old tutorial's HibernateTemplate is covered in the README's "legacy" note: it
 * cannot run on Hibernate 6.
 */
public class EntityStatesDemo {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(HibernateConfig.class)) {
            SessionFactory sessionFactory = context.getBean(SessionFactory.class);
            Statistics stats = sessionFactory.getStatistics();

            // 1. transient -> persistent
            Student student = new Student("Asha", "Pune");
            System.out.println("1. transient, id = " + student.getStudentId());
            sessionFactory.inTransaction(session -> session.persist(student));
            System.out.println("   after persist + commit, id = " + student.getStudentId());
            int id = student.getStudentId();

            // 2. dirty checking: change a persistent object, never call update()
            sessionFactory.inTransaction(session -> {
                Student loaded = session.get(Student.class, id);
                loaded.setStudentCity("Mumbai");
            });
            System.out.println("2. dirty checking: city in DB is now " + readCity(sessionFactory, id) + " (no update() called)");

            // 3. first-level cache: the same id twice in ONE session = one SELECT, same object
            stats.clear();
            sessionFactory.inTransaction(session -> {
                Student first = session.get(Student.class, id);
                Student second = session.get(Student.class, id);
                System.out.println("3. same object? " + (first == second) + ", SELECTs sent: " + stats.getPrepareStatementCount());
            });

            // 4. detached: the Session is closed, so the change is ignored...
            student.setStudentCity("Delhi");
            System.out.println("4. detached change: city in DB is still " + readCity(sessionFactory, id));
            // ...until merge() copies it onto a persistent instance
            sessionFactory.inTransaction(session -> session.merge(student));
            System.out.println("   after merge():     city in DB is " + readCity(sessionFactory, id));
        }
    }

    private static String readCity(SessionFactory sessionFactory, int id) {
        return sessionFactory.fromTransaction(session -> session.get(Student.class, id).getStudentCity());
    }
}
