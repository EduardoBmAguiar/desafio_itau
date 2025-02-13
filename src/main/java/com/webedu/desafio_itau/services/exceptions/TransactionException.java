package com.webedu.desafio_itau.services.exceptions;

public class TransactionException extends RuntimeException{

    public TransactionException(String msg) {
        super(msg);
    }
}
