package com.udemy.web.services.restful_web_services.topic06_versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Try this : curl localhost:8080/person                          -> v1 (the default)
 *            curl -H "X-API-Version: 2" localhost:8080/person    -> v2
 *            curl localhost:8080/v1/person  and  /v2/person      -> URI versioning, the classic
 *                                                                  way (just two different paths)
 * Trade-off: URI versions are easy to test in a browser and easy to cache. Header versions keep
 *            the URL the same. Pick ONE style for an API and stick to it.
 */
@RestController
public class VersioningController {

    @GetMapping(path = "/person", version = "1")
    public PersonV1 personV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", version = "2")
    public PersonV2 personV2() {
        return new PersonV2(new PersonV2.Name("Bob", "Charlie"));
    }

    @GetMapping("/v1/person")
    public PersonV1 uriV1() {
        return personV1();
    }

    @GetMapping("/v2/person")
    public PersonV2 uriV2() {
        return personV2();
    }
}
