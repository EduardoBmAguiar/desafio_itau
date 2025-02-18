package com.webedu.desafio_itau.services;

import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ValidateTransaction validateTransaction;

    public Transaction save(Transaction transaction) {

        this.validateTransaction.validateTransaction(transaction);

        return transactionRepository.save(transaction);
    }

    public void clear(Transaction transaction) {
        transactionRepository.clear();
    }

    public List<Transaction> findAll() {
        return transactionRepository.getTransactions();
    }
}
