package com.webedu.desafio_itau.controllers;

import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.services.TransactionService;
import com.webedu.desafio_itau.services.exceptions.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            transaction = transactionService.save(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);

        } catch (TransactionException e) {
            throw new TransactionException(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Transaction transaction) {
        transactionService.clear(transaction);
        return ResponseEntity.ok().body("Transactions deleted");
    }

    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<Transaction>> findAll() {
        List<Transaction> transactions = transactionService.findAll();
        return ResponseEntity.ok().body(transactions);
    }
}
