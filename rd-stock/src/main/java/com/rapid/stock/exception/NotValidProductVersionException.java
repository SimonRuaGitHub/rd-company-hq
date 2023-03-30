package com.rapid.stock.exception;

public class NotValidProductVersionException extends RuntimeException{
    public NotValidProductVersionException(String message) {
        super(message);
    }
}
