package com.springcore.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.springcore.jdbc.topic05_transactions.AccountRepository;
import com.springcore.jdbc.topic05_transactions.TransactionConfig;
import com.springcore.jdbc.topic05_transactions.TransferService;

@SpringJUnitConfig(TransactionConfig.class)
@TestPropertySource(properties = "DB_URL=")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TransferServiceTest {

    @Autowired
    AccountRepository accounts;

    @Autowired
    TransferService transfers;

    @BeforeEach
    void openAccounts() {
        accounts.create(1, "Asha", 500);
        accounts.create(2, "Ravi", 100);
    }

    @Test
    void successfulTransferMovesTheMoney() {
        transfers.transfer(1, 2, 200);
        assertEquals(300, accounts.balance(1));
        assertEquals(300, accounts.balance(2));
    }

    @Test
    void failedTransferRollsBackTheCredit() {
        assertThrows(DataAccessException.class, () -> transfers.transfer(1, 2, 1000));
        assertEquals(500, accounts.balance(1));
        assertEquals(100, accounts.balance(2));   // the money added to Ravi was taken back (rolled back)
    }

    @Test
    void withoutATransactionTheCreditIsKept() {
        assertThrows(DataAccessException.class, () -> transfers.transferWithoutTransaction(1, 2, 1000));
        assertEquals(1100, accounts.balance(2));  // money created out of nothing - exactly the bug that transactions prevent
    }
}
