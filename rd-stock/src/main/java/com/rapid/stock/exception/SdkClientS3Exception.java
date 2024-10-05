package com.rapid.stock.exception;

import com.amazonaws.SdkClientException;

public class SdkClientS3Exception extends SdkClientException {
    public SdkClientS3Exception(String message) {
        super(message);
    }
}
