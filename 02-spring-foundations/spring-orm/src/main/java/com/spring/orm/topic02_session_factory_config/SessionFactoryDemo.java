package com.spring.orm.topic02_session_factory_config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Run      : ./mvnw -q -pl spring-orm compile exec:java -Dexec.mainClass=com.spring.orm.topic02_session_factory_config.SessionFactoryDemo
 * Key idea : Java config and XML config build the same SessionFactory with the same entities.
 * Try this : add -Dshow.sql=true to see the CREATE TABLE statement Hibernate generates.
 */
public class SessionFactoryDemo {

    public static void main(String[] args) {
        try (var javaConfig = new AnnotationConfigApplicationContext(HibernateConfig.class)) {
            describe("Java config (HibernateConfig)", javaConfig.getBean(SessionFactory.class));
        }
        try (var xmlConfig = new ClassPathXmlApplicationContext("com/spring/orm/topic02_session_factory_config/hibernate-config.xml")) {
            describe("XML config (hibernate-config.xml)", xmlConfig.getBean(SessionFactory.class));
        }
    }

    private static void describe(String label, SessionFactory sessionFactory) {
        System.out.println(label + ":");
        System.out.println("  SessionFactory is open: " + sessionFactory.isOpen());
        sessionFactory.getMetamodel().getEntities()
                .forEach(entity -> System.out.println("  mapped entity: " + entity.getName() + " -> " + entity.getJavaType().getName()));
    }
}
