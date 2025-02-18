package com.webedu.desafio_itau.services;

import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.services.exceptions.TransactionException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class ValidateTransaction {

    public void validateTransaction(Transaction transaction) {
        if (transaction.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new TransactionException("negative value");
        }
        if (transaction.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new TransactionException("invalid date time");
        }
    }

}
