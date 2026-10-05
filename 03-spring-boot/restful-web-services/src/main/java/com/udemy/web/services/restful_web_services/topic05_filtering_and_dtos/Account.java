package com.udemy.web.services.restful_web_services.topic05_filtering_and_dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/*
 * Topic    : Controlling what goes into the JSON
 * Key idea : Three tools to decide which fields go into the JSON, from simplest to most flexible:
 *   @JsonIgnore - this field is NEVER sent (for secrets like passwordHash)
 *   @JsonView   - the same class, but a different set of fields for each endpoint (Views.Public / Views.Internal)
 *   a DTO       - a separate record with exactly the fields one endpoint needs (AccountSummary).
 *                 The clearest option, and the one to prefer in real APIs.
 *   Like a passbook: the customer sees the balance, the bank staff see more, nobody sees the PIN.
 * Note     : Jackson 3 (Spring Boot 4) moved its code to tools.jackson.*, but the ANNOTATIONS
 *            are still in com.fasterxml.jackson.annotation - so old tutorials still apply here.
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

        interface Internal extends Public {     // Internal = everything that Public has, plus more
        }
    }
}
