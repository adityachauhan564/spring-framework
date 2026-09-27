package com.springcore.jdbc.topic05_transactions;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * A transfer is TWO updates: credit the receiver, debit the sender.
 * They must succeed together or not at all (atomicity).
 *
 * @Transactional: Spring's proxy (spring-core topic13 AOP) starts a transaction before
 * the method, COMMITS if it returns normally, and ROLLS BACK if it throws a
 * RuntimeException. Checked exceptions do NOT roll back unless you say rollbackFor = ...
 */
@Service
public class TransferService {

    private final AccountRepository accounts;

    public TransferService(AccountRepository accounts) {
        this.accounts = accounts;
    }

    @Transactional
    public void transfer(int fromId, int toId, int amount) {
        accounts.changeBalance(toId, +amount);    // 1. credit first...
        accounts.changeBalance(fromId, -amount);  // 2. ...then debit: fails if the balance would go below 0
    }

    // the same code WITHOUT a transaction: step 1 stays committed even when step 2 fails
    public void transferWithoutTransaction(int fromId, int toId, int amount) {
        accounts.changeBalance(toId, +amount);
        accounts.changeBalance(fromId, -amount);
    }
}
