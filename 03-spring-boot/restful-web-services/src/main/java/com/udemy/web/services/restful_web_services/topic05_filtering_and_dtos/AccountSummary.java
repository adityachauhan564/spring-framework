package com.udemy.web.services.restful_web_services.topic05_filtering_and_dtos;

/* A DTO (Data Transfer Object): the exact response shape for one endpoint, separate from Account. */
public record AccountSummary(int id, String owner) {

    static AccountSummary from(Account account) {
        return new AccountSummary(account.id(), account.owner());
    }
}
