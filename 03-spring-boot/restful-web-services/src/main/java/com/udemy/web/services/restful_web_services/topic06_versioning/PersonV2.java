package com.udemy.web.services.restful_web_services.topic06_versioning;

/* Version 2: the name is split into two fields. This would break v1 clients, so it needs a new version. */
public record PersonV2(Name name) {

    public record Name(String firstName, String lastName) {
    }
}
