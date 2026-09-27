package com.springcore.topic05_xml_autowiring;

/*
 * Has BOTH a setter and a constructor for Address, so the same class can be
 * autowired byName / byType (setter) and by constructor.
 * The old version had setAdress(...) - a typo that silently breaks byName,
 * because Spring derives the property name "adress" from the setter.
 */
public class Employee {

    private Address address;
    private String wiredBy = "setter";

    public Employee() {
    }

    public Employee(Address address) {
        this.address = address;
        this.wiredBy = "constructor";
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee[address=" + address + ", injected via " + wiredBy + "]";
    }
}
