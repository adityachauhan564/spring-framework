package com.springcore.topic03_constructor_injection;

/*
 * No setters and final fields: the only way to build a Person is through the
 * constructor, so a Person is always complete. That's why constructor injection
 * is the recommended default for REQUIRED dependencies.
 */
public class Person {

    private final String name;
    private final int personId;

    public Person(String name, int personId) {
        this.name = name;
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Person[name=" + name + ", personId=" + personId + "]";
    }
}
