package com.webedu.desafio_itau.services;

import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.exceptions.TransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Slf4j
@Service
public class ValidateTransaction {

    public void validateTransaction(Transaction transaction) {
        if (transaction.getValor().compareTo(BigDecimal.ZERO) < 0) {
            log.error("Negative value");
            throw new TransactionException("negative value");
        }
        if (transaction.getDataHora().isAfter(OffsetDateTime.now())) {
            log.error("Invalid date time");
            throw new TransactionException("invalid date time");
        }
    }
}
