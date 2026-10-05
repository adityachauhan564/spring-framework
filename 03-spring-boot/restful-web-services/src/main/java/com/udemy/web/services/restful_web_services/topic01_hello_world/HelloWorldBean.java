package com.udemy.web.services.restful_web_services.topic01_hello_world;

/*
 * A record: Jackson (the JSON library) turns it into {"message":"..."} - one JSON field for each component.
 * (The old version was a class with a constructor, getter, setter and toString.
 * A record gives you all of that in one line.)
 */
public record HelloWorldBean(String message) {
}
