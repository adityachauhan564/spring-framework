package com.udemy.web.services.restful_web_services.topic02_request_data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.web.services.restful_web_services.topic01_hello_world.HelloWorldBean;

/*
 * Topic    : Reading data from a request
 * Key idea : A client can put data in three places in a GET request:
 *   path     /hello-world/path-variable/Adi   -> @PathVariable  (says WHICH thing, like a house number)
 *   query    /greet?name=Adi&times=2          -> @RequestParam  (extra options, filters, paging)
 *   header   Accept-Language: hi              -> @RequestHeader (information about the request itself)
 *   A JSON body (@RequestBody) comes with POST/PUT - see topic03.
 * Try this : curl "localhost:8080/greet?name=Adi&times=3"
 *            curl -H "Accept-Language: hi" localhost:8080/greet
 */
@RestController
public class RequestDataController {

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean pathVariable(@PathVariable String name) {
        return new HelloWorldBean("Hello World, " + name);
    }

    @GetMapping("/greet")
    public HelloWorldBean greet(@RequestParam(defaultValue = "guest") String name,
                                @RequestParam(defaultValue = "1") int times,
                                @RequestHeader(name = "Accept-Language", defaultValue = "en") String language) {
        String hello = language.startsWith("hi") ? "Namaste" : "Hello";
        return new HelloWorldBean((hello + " " + name + "! ").repeat(Math.max(1, Math.min(times, 5))).trim());
    }
}
