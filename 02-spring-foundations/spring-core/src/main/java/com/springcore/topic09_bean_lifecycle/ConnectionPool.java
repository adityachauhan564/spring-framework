package com.springcore.topic09_bean_lifecycle;

/*
 * Way 1: plain methods, named in XML with init-method / destroy-method.
 * The class has no Spring imports at all - handy for classes you can't change.
 */
public class ConnectionPool {

    private int size;

    public ConnectionPool() {
        System.out.println("  [ConnectionPool] 1. constructor");
    }

    public void setSize(int size) {
        System.out.println("  [ConnectionPool] 2. setter: size=" + size);
        this.size = size;
    }

    public void open() {
        System.out.println("  [ConnectionPool] 3. init-method open(): opening " + size + " connections");
    }

    public void close() {
        System.out.println("  [ConnectionPool] destroy-method close(): closing connections");
    }
}
