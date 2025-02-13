package com.webedu.desafio_itau.repositories;

import com.webedu.desafio_itau.entities.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    public Transaction save(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }

    public void clear() {
        transactions.clear();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
