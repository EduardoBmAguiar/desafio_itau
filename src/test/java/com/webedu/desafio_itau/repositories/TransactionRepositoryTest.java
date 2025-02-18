package com.webedu.desafio_itau.repositories;

import com.webedu.desafio_itau.entities.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName("Should save Transaction successfully")
    void saveCase1() {

        Transaction transaction = new Transaction(BigDecimal.valueOf(100.00), OffsetDateTime.now());

        Transaction result = this.transactionRepository.save(transaction);

        assertThat(result.getValor()).isEqualTo(transaction.getValor());
    }

    @Test
    @DisplayName("Should save Transaction don't success (Value is null)")
    void saveCase2() {

        Transaction transaction = new Transaction(BigDecimal.valueOf(100.00), OffsetDateTime.now());

        Transaction result = this.transactionRepository.save(transaction);

        assertThat(result.getValor()).isNotNull();
    }

    @Test
    @DisplayName("Should save Transaction don't success (Date is null)")
    void saveCase3() {

        Transaction transaction = new Transaction(BigDecimal.valueOf(100.00), OffsetDateTime.now());

        Transaction result = this.transactionRepository.save(transaction);

        assertThat(result.getDataHora()).isNotNull();
    }
}