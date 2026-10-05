package com.example.udemy.microservices.currency_exchange_service;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/*
 * One exchange rate, e.g. 1 USD = 91 INR. The rows come from data.sql.
 * "from" and "to" are RESERVED words in SQL: a column called just "from" breaks the
 * CREATE TABLE statement (the error the old Url.txt recorded). @Column gives them other names.
 * BigDecimal, not double: money must never have rounding errors (with double, 0.1 + 0.2 is not exactly 0.3).
 */
@Entity
public class CurrencyExchange {

    @Id
    private Long id;

    @Column(name = "currency_from")
    private String from;

    @Column(name = "currency_to")
    private String to;

    private BigDecimal conversionMultiple;

    protected CurrencyExchange() {
        // needed by JPA: it creates an empty object first, then fills the fields
    }

    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }
}
