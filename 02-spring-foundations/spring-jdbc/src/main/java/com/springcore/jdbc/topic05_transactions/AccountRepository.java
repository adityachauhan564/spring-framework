package com.springcore.jdbc.topic05_transactions;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/*
 * Topic    : Transactions
 * Read     : AccountRepository -> TransferService -> TransactionConfig -> TransactionsDemo
 * Each method here runs just ONE SQL statement.
 * Whether several statements succeed or fail TOGETHER is decided one level up, in the service.
 */
@Repository
public class AccountRepository {

    private final JdbcTemplate jdbc;

    public AccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void create(int id, String owner, int balance) {
        jdbc.update("insert into account (id, owner, balance) values (?, ?, ?)", id, owner, balance);
    }

    public void changeBalance(int id, int delta) {
        // the CHECK (balance >= 0) rule in schema.sql refuses to let a balance go below zero
        jdbc.update("update account set balance = balance + ? where id = ?", delta, id);
    }

    public int balance(int id) {
        return jdbc.queryForObject("select balance from account where id = ?", Integer.class, id);
    }
}
