package com.rapid.stock.exception;

public class NotValidProductException extends RuntimeException{
    public NotValidProductException(String message) {
        super(message);
    }
}
