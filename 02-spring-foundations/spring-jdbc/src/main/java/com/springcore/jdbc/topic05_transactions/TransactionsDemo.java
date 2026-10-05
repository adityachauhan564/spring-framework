package com.springcore.jdbc.topic05_transactions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;

/*
 * Run      : ./mvnw -q -pl spring-jdbc compile exec:java -Dexec.mainClass=com.springcore.jdbc.topic05_transactions.TransactionsDemo
 * Key idea : A transaction = a group of steps that either ALL happen, or NONE happen.
 *            - A failing transfer WITH @Transactional leaves both balances as they were.
 *            - WITHOUT it, the receiver keeps money that the sender never paid.
 *            - Like a UPI payment: if it fails midway, the money comes back to your account.
 * Try this : Make transfer() throw a checked exception. Does it still roll back?
 */
public class TransactionsDemo {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(TransactionConfig.class)) {
            AccountRepository accounts = context.getBean(AccountRepository.class);
            TransferService transfers = context.getBean(TransferService.class);
            accounts.create(1, "Asha", 500);
            accounts.create(2, "Ravi", 100);
            print("start", accounts);

            transfers.transfer(1, 2, 200);
            print("transfer 200 (ok)", accounts);

            attempt(() -> transfers.transfer(1, 2, 1000));
            print("transfer 1000 WITH @Transactional", accounts);

            attempt(() -> transfers.transferWithoutTransaction(1, 2, 1000));
            print("transfer 1000 WITHOUT a transaction", accounts);
        }
    }

    private static void attempt(Runnable transfer) {
        try {
            transfer.run();
        } catch (DataAccessException e) {
            System.out.println("  failed: balance would go below 0 (CHECK constraint)");
        }
    }

    private static void print(String step, AccountRepository accounts) {
        System.out.printf("%-38s Asha=%4d  Ravi=%4d  total=%d%n",
                step + ":", accounts.balance(1), accounts.balance(2), accounts.balance(1) + accounts.balance(2));
    }
}
