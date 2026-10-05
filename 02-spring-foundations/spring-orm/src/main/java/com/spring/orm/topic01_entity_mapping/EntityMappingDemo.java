package com.spring.orm.topic01_entity_mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.orm.topic02_session_factory_config.HibernateConfig;

/*
 * Run      : ./mvnw -q -pl spring-orm compile exec:java -Dexec.mainClass=com.spring.orm.topic01_entity_mapping.EntityMappingDemo
 * Key idea : You never wrote CREATE TABLE. Hibernate made the student_detail table by itself,
 *            just by reading the annotations on Student.
 *            This demo reads the table details back from the database to prove it.
 *            (How HibernateConfig is wired is explained in topic02.)
 * Try this : Rename a field that has no @Column and run again - the column name changes with the field.
 */
public class EntityMappingDemo {

    public static void main(String[] args) throws SQLException {
        try (var context = new AnnotationConfigApplicationContext(HibernateConfig.class);
             Connection connection = context.getBean(DataSource.class).getConnection();
             ResultSet columns = connection.getMetaData().getColumns(null, null, "STUDENT_DETAIL", null)) {

            System.out.println("Table STUDENT_DETAIL, generated from @Entity Student:");
            while (columns.next()) {
                System.out.printf("  %-13s %-17s nullable=%s%n", columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME") + "(" + columns.getInt("COLUMN_SIZE") + ")",
                        columns.getString("IS_NULLABLE"));
            }
        }
    }
}
