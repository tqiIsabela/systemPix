package com.github.tqiIsabela.systemPix.exception;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException() {
        super("Saldo insuficiente para realizar a transação.");
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}

