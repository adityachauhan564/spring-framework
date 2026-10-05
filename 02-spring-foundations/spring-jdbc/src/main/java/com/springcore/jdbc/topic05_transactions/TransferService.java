package com.springcore.jdbc.topic05_transactions;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * A transfer is TWO updates: add money to the receiver, take money from the sender.
 * Both must succeed together, or neither should happen (this is called atomicity).
 *
 * @Transactional: Spring's proxy (see spring-core topic13, AOP) starts a transaction before
 * the method runs. It COMMITS (saves) if the method finishes normally, and ROLLS BACK (undoes)
 * if it throws a RuntimeException.
 * Checked exceptions do NOT roll back, unless you say so with rollbackFor = ...
 */
@Service
public class TransferService {

    private final AccountRepository accounts;

    public TransferService(AccountRepository accounts) {
        this.accounts = accounts;
    }

    @Transactional
    public void transfer(int fromId, int toId, int amount) {
        accounts.changeBalance(toId, +amount);    // 1. first add money to the receiver...
        accounts.changeBalance(fromId, -amount);  // 2. ...then take it from the sender: fails if the balance would go below 0
    }

    // the same code WITHOUT a transaction: step 1 stays saved even when step 2 fails
    public void transferWithoutTransaction(int fromId, int toId, int amount) {
        accounts.changeBalance(toId, +amount);
        accounts.changeBalance(fromId, -amount);
    }
}
