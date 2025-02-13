package com.webedu.desafio_itau.controllers;

import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.services.TransactionService;
import com.webedu.desafio_itau.services.exceptions.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid Transaction, date or amount not accepted");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Json");
        }
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        List<Transaction> transactions = transactionService.findAll();
        return ResponseEntity.ok(transactions);
    }

}
