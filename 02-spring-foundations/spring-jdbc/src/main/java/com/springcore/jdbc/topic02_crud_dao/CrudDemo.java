package com.springcore.jdbc.topic02_crud_dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcore.jdbc.topic01_datasource_and_jdbctemplate.JdbcConfig;

/*
 * Run      : ./mvnw -q -pl spring-jdbc compile exec:java -Dexec.mainClass=com.springcore.jdbc.topic02_crud_dao.CrudDemo
 * Key idea : the demo only talks to StudentDao - Create, Read, Update, Delete - and never sees SQL.
 * Try this : add a findByCity(String city) method to the DAO.
 */
public class CrudDemo {

    public static void main(String[] args) {
        // register the config class AND the DAO class directly - no component scan needed
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
