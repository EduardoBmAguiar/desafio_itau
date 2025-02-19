package com.webedu.desafio_itau.controllers;

import com.webedu.desafio_itau.annotation.TrackExecutionTime;
import com.webedu.desafio_itau.entities.Transaction;
import com.webedu.desafio_itau.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @TrackExecutionTime
    @Operation(description = "Está requizição faz o salvamento de uma transação na memória.", summary = "Realiza o salvamento da transação", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação criada"),
            @ApiResponse(responseCode = "400", description = "Json Invalido"),
            @ApiResponse(responseCode = "422", description = "Transação não aprovada")
    })
    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody Transaction transaction) {

            log.info("Trying to save a transition");
            transaction = transactionService.save(transaction);
            log.info("Transaction saved");
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);

    }

    @TrackExecutionTime
    @Operation(description = "Está requizição faz a deleção de todas as transações salvas na memória.", summary = "Realiza a deleção de todas as transações", method = "DELETE")
    @ApiResponse(responseCode = "200", description = "Transações deletadas")
    @DeleteMapping
    public ResponseEntity<Void> delete() {
        log.info("Trying to delete a transitions");
        transactionService.clear();
        log.info("Transactions deleted");
        return ResponseEntity.ok().build();
    }

    @TrackExecutionTime
    @Operation(description = "Está requizição faz a busca por todas as transações salvas na memória.", summary = "Realiza a busca pelas transações", method = "GET")
    @ApiResponse(responseCode = "200", description = "Transações retornadas")
    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<Transaction>> findAll() {
        log.info("Trying to find all transactions");
        List<Transaction> transactions = transactionService.findAll();
        log.info("Transactions found");
        return ResponseEntity.ok().body(transactions);
    }
}
