package com.udemy.web.services.restful_web_services.topic05_filtering_and_dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/*
 * Topic    : Controlling what goes into the JSON
 * Key idea : three tools, from simplest to most flexible:
 *   @JsonIgnore - a field is NEVER sent (secrets: passwordHash)
 *   @JsonView   - the same class, different field sets per endpoint (Views.Public / Views.Internal)
 *   a DTO       - a separate record with exactly the fields one endpoint needs (AccountSummary);
 *                 the most explicit option, and the one to prefer in real APIs
 * Note     : Jackson 3 (Spring Boot 4) moved its code to tools.jackson.*, but the ANNOTATIONS
 *            are still in com.fasterxml.jackson.annotation - old tutorials still apply here.
 */
public record Account(
        @JsonView(Views.Public.class) int id,
        @JsonView(Views.Public.class) String owner,
        @JsonView(Views.Internal.class) String email,
        @JsonView(Views.Internal.class) long balance,
        @JsonIgnore String passwordHash) {

    public interface Views {
        interface Public {
        }

        interface Internal extends Public {     // Internal = everything Public has, plus more
        }
    }
}
