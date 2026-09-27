package com.springcore.topic05_xml_autowiring;

public class Address {

    private String street;
    private String city;

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return street + ", " + city;
    }
}
