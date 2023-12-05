package com.github.tqiIsabela.systemPix.exception;

public class PixKeyNotFoundException extends RuntimeException {

    public PixKeyNotFoundException(String message){
        super(message);
    }

    public PixKeyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
