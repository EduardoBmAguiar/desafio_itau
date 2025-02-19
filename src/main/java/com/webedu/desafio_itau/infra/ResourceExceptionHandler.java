package com.webedu.desafio_itau.infra;


import com.webedu.desafio_itau.exceptions.TransactionException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<StandardError> transactionException(TransactionException e, HttpServletRequest request) {
        String error = "TransactionException";
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandardError err = new StandardError(OffsetDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        log.error("Error transaction exception");
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> transactionException(Exception e, HttpServletRequest request) {
        String error = "Exception";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(OffsetDateTime.now(), status.value(), error, "INVALID JSON", request.getRequestURI());
        log.error("unexpected error");
        return ResponseEntity.status(status).body(err);
    }
}