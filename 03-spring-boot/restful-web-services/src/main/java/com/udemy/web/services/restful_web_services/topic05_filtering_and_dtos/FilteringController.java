package com.udemy.web.services.restful_web_services.topic05_filtering_and_dtos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

/*
 * Try this : curl localhost:8080/accounts/1           (all fields except passwordHash)
 *            curl localhost:8080/accounts/1/public    (@JsonView Public: id, owner)
 *            curl localhost:8080/accounts/1/internal  (@JsonView Internal: id, owner, email, balance)
 *            curl localhost:8080/accounts/1/summary   (the AccountSummary DTO)
 */
@RestController
@RequestMapping("/accounts")
public class FilteringController {

    private static final Account ACCOUNT = new Account(1, "Asha", "asha@example.com", 25_000, "$2a$10$notARealHash");

    @GetMapping("/1")
    public Account full() {
        return ACCOUNT;
    }

    @GetMapping("/1/public")
    @JsonView(Account.Views.Public.class)
    public Account publicView() {
        return ACCOUNT;
    }

    @GetMapping("/1/internal")
    @JsonView(Account.Views.Internal.class)
    public Account internalView() {
        return ACCOUNT;
    }

    @GetMapping("/1/summary")
    public AccountSummary summary() {
        return AccountSummary.from(ACCOUNT);
    }
}
