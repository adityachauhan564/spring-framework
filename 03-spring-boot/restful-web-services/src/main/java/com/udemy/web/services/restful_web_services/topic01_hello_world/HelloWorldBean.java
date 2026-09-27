package com.udemy.web.services.restful_web_services.topic01_hello_world;

/*
 * A record: Jackson turns it into {"message":"..."} - one JSON field per component.
 * (The old version was a class with a constructor, getter, setter and toString; a record
 * gives all of that in one line.)
 */
public record HelloWorldBean(String message) {
}
