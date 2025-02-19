package com.webedu.desafio_itau.exceptions;

public class TransactionException extends RuntimeException{

    public TransactionException(String msg) {
        super(msg);
    }
}
