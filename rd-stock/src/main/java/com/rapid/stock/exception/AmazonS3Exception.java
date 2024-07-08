package com.rapid.stock.exception;

import com.amazonaws.AmazonServiceException;

public class AmazonS3Exception extends AmazonServiceException {
    public AmazonS3Exception(String message) {
        super(message);
    }
}

