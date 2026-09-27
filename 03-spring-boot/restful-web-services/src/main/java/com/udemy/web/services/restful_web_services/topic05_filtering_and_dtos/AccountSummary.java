package com.udemy.web.services.restful_web_services.topic05_filtering_and_dtos;

/* A DTO (Data Transfer Object): the response shape for one endpoint, independent of Account. */
public record AccountSummary(int id, String owner) {

    static AccountSummary from(Account account) {
        return new AccountSummary(account.id(), account.owner());
    }
}
