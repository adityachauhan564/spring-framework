package com.springcore.topic04_injecting_collections;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* Receives each collection type through a setter. */
public class Employee {

    private String name;
    private List<String> phones;
    private Set<String> addresses;
    private Map<String, String> courses;
    private Properties settings;

    public void setName(String name) {
        this.name = name;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public void setAddresses(Set<String> addresses) {
        this.addresses = addresses;
    }

    public void setCourses(Map<String, String> courses) {
        this.courses = courses;
    }

    public void setSettings(Properties settings) {
        this.settings = settings;
    }

    public List<String> getPhones() {
        return phones;
    }

    public Set<String> getAddresses() {
        return addresses;
    }

    public Map<String, String> getCourses() {
        return courses;
    }

    public Properties getSettings() {
        return settings;
    }

    public String getName() {
        return name;
    }
}
