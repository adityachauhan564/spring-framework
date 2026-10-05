package com.springcore.jdbc.topic02_crud_dao;

/*
 * Topic    : CRUD with a DAO (Data Access Object)
 * Read     : Student -> StudentDao -> StudentRowMapper -> StudentDaoImpl -> CrudDemo
 * A plain class: one Student object = one row of the student table.
 * The no-arg constructor and the setters are there because topic03's
 * BeanPropertyRowMapper needs them.
 */
public class Student {

    private int id;
    private String name;
    private String city;

    public Student() {
    }

    public Student(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student[id=" + id + ", name=" + name + ", city=" + city + "]";
    }
}
