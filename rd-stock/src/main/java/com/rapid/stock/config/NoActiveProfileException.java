package com.rapid.stock.config;

import lombok.experimental.SuperBuilder;


public class NoActiveProfileException extends RuntimeException {
    public NoActiveProfileException(String message) {
        super(message);
    }
}
