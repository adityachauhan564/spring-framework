package com.spring.orm.topic03_hibernate_crud_and_hql;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.orm.topic01_entity_mapping.Student;
import com.spring.orm.topic02_session_factory_config.HibernateConfig;

/*
 * Run      : ./mvnw -q -pl spring-orm compile exec:java -Dexec.mainClass=com.spring.orm.topic03_hibernate_crud_and_hql.HibernateCrudDemo
 *            add -Dshow.sql=true to see every SQL statement Hibernate writes for you
 * Try this : add findByNameStartingWith(String prefix) using HQL "like".
 */
public class HibernateCrudDemo {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(HibernateConfig.class, StudentDao.class)) {
            StudentDao dao = context.getBean(StudentDao.class);

            Student aditya = dao.save(new Student("Aditya Chauhan", "Lucknow"));
            dao.save(new Student("Khushi Chauhan", "Lucknow"));
            dao.save(new Student("Roshan Chauhan", "Dehradun"));
            System.out.println("saved, generated id: " + aditya.getStudentId());

            System.out.println("findAll:            " + dao.findAll());
            System.out.println("findByCity(Lucknow): " + dao.findByCity("Lucknow"));

            dao.changeCity(aditya.getStudentId(), "Pune");
            System.out.println("after changeCity:   " + dao.findById(aditya.getStudentId()).orElseThrow());

            dao.delete(aditya.getStudentId());
            System.out.println("after delete:       " + dao.findById(aditya.getStudentId()).map(Student::toString).orElse("not found"));
        }
    }
}
