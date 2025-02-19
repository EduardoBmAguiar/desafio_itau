package com.webedu.desafio_itau.controllers;

import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.services.TransactionService;
import com.webedu.desafio_itau.services.exceptions.TransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/transacao",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity save(@RequestBody Transaction transaction) {
        try {
            log.info("Trying to save a transition");
            transaction = transactionService.save(transaction);
            log.info("Transaction saved");
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);

        } catch (TransactionException e) {
            log.error("Don´t save a transition");
            throw new TransactionException(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Transaction transaction) {
        log.info("Trying to delete a transitions");
        transactionService.clear(transaction);
        log.info("Transactions deleted");
        return ResponseEntity.ok().body("Transactions deleted");
    }

    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<Transaction>> findAll() {
        log.info("Trying to find all transactions");
        List<Transaction> transactions = transactionService.findAll();
        log.info("Transactions found");
        return ResponseEntity.ok().body(transactions);
    }
}
