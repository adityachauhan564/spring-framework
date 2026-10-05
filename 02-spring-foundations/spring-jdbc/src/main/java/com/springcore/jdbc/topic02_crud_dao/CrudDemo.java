package com.springcore.jdbc.topic02_crud_dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcore.jdbc.topic01_datasource_and_jdbctemplate.JdbcConfig;

/*
 * Run      : ./mvnw -q -pl spring-jdbc compile exec:java -Dexec.mainClass=com.springcore.jdbc.topic02_crud_dao.CrudDemo
 * Key idea : CRUD = Create, Read, Update, Delete - the four basic things you do with data.
 *            This demo only talks to StudentDao, and never sees any SQL.
 *            Like ordering at a restaurant: you talk to the waiter (DAO), not to the kitchen (SQL).
 * Try this : Add a findByCity(String city) method to the DAO.
 */
public class CrudDemo {

    public static void main(String[] args) {
        // give Spring the config class AND the DAO class directly, so no component scan is needed
        try (var context = new AnnotationConfigApplicationContext(JdbcConfig.class, StudentDaoImpl.class)) {
            StudentDao dao = context.getBean(StudentDao.class);

            System.out.println("CREATE: " + dao.insert(new Student(777, "Roshan Chauhan", "Dehradun")) + " row");
            dao.insert(new Student(778, "Aditya Chauhan", "Lucknow"));
            System.out.println("READ all:        " + dao.findAll());

            System.out.println("UPDATE: " + dao.update(new Student(777, "Roshan Chauhan", "Lucknow")) + " row");
            System.out.println("READ 777:        " + dao.findById(777).orElseThrow());

            System.out.println("DELETE: " + dao.delete(777) + " row");
            System.out.println("READ 777 again:  " + dao.findById(777).map(Student::toString).orElse("not found (no exception)"));
            System.out.println("READ all:        " + dao.findAll());
        }
    }
}
