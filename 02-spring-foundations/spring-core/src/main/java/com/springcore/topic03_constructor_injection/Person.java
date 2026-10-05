package com.springcore.topic03_constructor_injection;

/*
 * No setters, and the fields are final (they can be set only once).
 * So the ONLY way to build a Person is through the constructor,
 * which means a Person is always complete - never missing a name or an id.
 * That is why constructor injection is the recommended default for REQUIRED dependencies.
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
