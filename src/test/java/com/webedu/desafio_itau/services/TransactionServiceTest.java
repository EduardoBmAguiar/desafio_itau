package com.webedu.desafio_itau.services;

import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.repositories.TransactionRepository;
import com.webedu.desafio_itau.exceptions.TransactionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Service
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ValidateTransaction validateTransaction;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create transaction successfully when everything is OK")
    void saveTransaction() {

        Transaction transaction = new Transaction(new BigDecimal(1000), OffsetDateTime.now());
        when(transactionRepository.save(any())).thenReturn(transaction);

        Transaction savedTransaction = transactionService.save(transaction);

        assertNotNull(savedTransaction);
        assertEquals(transaction, savedTransaction);

        verify(validateTransaction, times(1)).validateTransaction(transaction);

        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    @DisplayName("Should throw Exception when Transaction is not allowed")
    void saveTransaction2() {

        Transaction transaction = new Transaction(new BigDecimal(1000), OffsetDateTime.now());

        doThrow(new TransactionException("negative value")).when(validateTransaction).validateTransaction(transaction);

        Exception exception = assertThrows(TransactionException.class, () -> transactionService.save(transaction));

        assertEquals("negative value", exception.getMessage());

        verify(transactionRepository, never()).save(any());
    }

}