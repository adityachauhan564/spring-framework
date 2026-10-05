package com.springcore.topic05_xml_autowiring;

/*
 * This class has BOTH a setter and a constructor for Address.
 * So the same class can be autowired byName / byType (using the setter)
 * and by constructor (using the constructor).
 * Watch out: an older version had setAdress(...) - one missing 'd'.
 * That typo silently breaks byName, because Spring takes the property name
 * from the setter name ("adress"), and no bean has that id. No error, just no address.
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
