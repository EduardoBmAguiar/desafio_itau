package com.webedu.desafio_itau.services;

import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.repositories.TransactionRepository;
import com.webedu.desafio_itau.services.exceptions.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction save(Transaction transaction) {

        ValidateTransaction(transaction);

        return transactionRepository.save(transaction);
    }

    private void ValidateTransaction(Transaction transaction) {
        if (transaction.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new TransactionException("negative value");
        }
        if (transaction.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new TransactionException("invalid date time");
        }
    }

    public void clear(Transaction transaction) {
        transactionRepository.clear();
    }

    public List<Transaction> findAll() {
        return transactionRepository.getTransactions();
    }
}
