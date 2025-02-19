package com.webedu.desafio_itau.repositories;

import com.webedu.desafio_itau.entities.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    public Transaction save(Transaction transaction) {
        log.info("Saving a new transaction");
        transactions.add(transaction);
        log.info("New transaction saved");
        return transaction;
    }

    public void clear() {
        log.info("Clearing all transactions");
        transactions.clear();
    }

    public List<Transaction> getTransactions() {
        log.info("Retrieving all transactions");
        return transactions;
    }
}
