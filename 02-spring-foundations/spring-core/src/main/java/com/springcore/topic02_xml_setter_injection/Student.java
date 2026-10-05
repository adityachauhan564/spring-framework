package com.springcore.topic02_xml_setter_injection;

/*
 * A plain Java class (called a "POJO" - Plain Old Java Object, no Spring code inside).
 * For setter injection, Spring needs only two things:
 *   - a no-argument constructor, so it can create an empty object
 *   - one setter for each property, so it can fill the values
 */
public class Student {

    private int studentId;
    private String studentName;
    private String studentAddress;

    public Student() {
        System.out.println("  Student() constructor called");
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        System.out.println("  setStudentName(\"" + studentName + "\") called by Spring");
        this.studentName = studentName;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    @Override
    public String toString() {
        return "Student[id=" + studentId + ", name=" + studentName + ", address=" + studentAddress + "]";
    }
}
