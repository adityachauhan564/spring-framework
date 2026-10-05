package com.spring.orm.topic04_entity_states_and_session;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.orm.topic01_entity_mapping.Student;
import com.spring.orm.topic02_session_factory_config.HibernateConfig;

/*
 * Topic    : Entity states and the Session - how Hibernate really behaves
 * Key idea : Every entity object is in one of these states:
 *              transient  - just made with new Student(...). Hibernate does not know about it yet.
 *              persistent - attached to an open Session. Changes are saved AUTOMATICALLY at commit
 *                           (this is "dirty checking"), and get() gives back the same object
 *                           (this is the "first-level cache").
 *              detached   - its Session is closed. Changes are NOT saved until you merge() it.
 *            - Like a library book: on the shelf (transient), issued in your name (persistent),
 *              taken home after the library closed (detached - the library can't see your notes).
 *            - This explains the two most common Hibernate surprises:
 *              "why did my change save without update()?" and "why didn't my change save?"
 * Run      : ./mvnw -q -pl spring-orm compile exec:java -Dexec.mainClass=com.spring.orm.topic04_entity_states_and_session.EntityStatesDemo
 * Try this : Call session.detach(student) before the setter in step 2. Is the change still saved?
 *
 * (Written with SessionFactory.inTransaction(...) instead of @Transactional, so you can see
 *  exactly where each Session starts and ends, right here in the code.)
 * The old tutorial's HibernateTemplate is covered in the README's "legacy" note:
 * it cannot run on Hibernate 6.
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

            // 2. dirty checking: change a persistent object, and never call update()
            sessionFactory.inTransaction(session -> {
                Student loaded = session.get(Student.class, id);
                loaded.setStudentCity("Mumbai");
            });
            System.out.println("2. dirty checking: city in DB is now " + readCity(sessionFactory, id) + " (no update() called)");

            // 3. first-level cache: asking for the same id twice in ONE session = only one SELECT, and the same object
            stats.clear();
            sessionFactory.inTransaction(session -> {
                Student first = session.get(Student.class, id);
                Student second = session.get(Student.class, id);
                System.out.println("3. same object? " + (first == second) + ", SELECTs sent: " + stats.getPrepareStatementCount());
            });

            // 4. detached: the Session is closed, so this change is ignored...
            student.setStudentCity("Delhi");
            System.out.println("4. detached change: city in DB is still " + readCity(sessionFactory, id));
            // ...until merge() copies it onto a persistent object
            sessionFactory.inTransaction(session -> session.merge(student));
            System.out.println("   after merge():     city in DB is " + readCity(sessionFactory, id));
        }
    }

    private static String readCity(SessionFactory sessionFactory, int id) {
        return sessionFactory.fromTransaction(session -> session.get(Student.class, id).getStudentCity());
    }
}
