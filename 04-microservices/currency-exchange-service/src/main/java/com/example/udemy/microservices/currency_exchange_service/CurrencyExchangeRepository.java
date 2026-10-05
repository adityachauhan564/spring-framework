package com.example.udemy.microservices.currency_exchange_service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/* Spring Data builds the query from the method name: ... where currency_from = ? and currency_to = ? */
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
