package com.example.udemy.microservices.currency_exchange_service;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/*
 * One exchange rate, e.g. 1 USD = 91 INR. Rows come from data.sql.
 * "from" and "to" are RESERVED words in SQL: a column literally called "from" breaks the
 * CREATE TABLE statement (the error the old Url.txt recorded). @Column renames them.
 * BigDecimal, not double: money must not have floating-point rounding errors.
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
        // required by JPA
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
