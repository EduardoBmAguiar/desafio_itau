package com.webedu.desafio_itau.services;

import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ValidateTransaction validateTransaction;

    public Transaction save(Transaction transaction) {
        log.info("Going to validate the transaction");
        this.validateTransaction.validateTransaction(transaction);
        log.info("Validating the transaction");
        return transactionRepository.save(transaction);
    }

    public void clear(Transaction transaction) {
        log.info("Going to delete the transaction");
        transactionRepository.clear();
    }

    public List<Transaction> findAll() {
        log.info("Going to find all transitions");
        return transactionRepository.getTransactions();
    }
}
