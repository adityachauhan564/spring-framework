package com.spring.orm.topic01_entity_mapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * Topic    : Entity mapping - a Java class that IS a table
 * Key idea : with JDBC (spring-jdbc) you wrote SQL and mapped rows by hand. With an ORM
 *            (Object-Relational Mapper) like Hibernate you annotate the class once and it
 *            writes the SQL for you.
 *              @Entity         - this class is stored in the database
 *              @Table          - which table (default: the class name)
 *              @Id             - the primary key
 *              @GeneratedValue - let the database choose the id
 *              @Column         - column name / constraints (default: the field name)
 * Run      : see EntityMappingDemo
 */
@Entity
@Table(name = "student_detail")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "student_name", nullable = false, length = 100)
    private String studentName;

    @Column(name = "student_city")
    private String studentCity;

    protected Student() {
        // required by JPA/Hibernate (it creates the object, then fills the fields)
    }

    public Student(String studentName, String studentCity) {
        this.studentName = studentName;
        this.studentCity = studentCity;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCity() {
        return studentCity;
    }

    public void setStudentCity(String studentCity) {
        this.studentCity = studentCity;
    }

    @Override
    public String toString() {
        return "Student[id=" + studentId + ", name=" + studentName + ", city=" + studentCity + "]";
    }
}
