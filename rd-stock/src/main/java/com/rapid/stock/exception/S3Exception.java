package com.rapid.stock.exception;

import com.amazonaws.AmazonServiceException;
import com.rapid.stock.model.v2.S3OperationType;
import lombok.Getter;

@Getter
public class S3Exception extends AmazonServiceException {

    private final String bucket;
    private final String key;
    private final int statusCode;
    private final S3OperationType operationType;

    public S3Exception(
            String message,
            String bucket,
            String key,
            int statusCode,
            S3OperationType operationType
    ) {
        super(message);
        this.bucket = bucket;
        this.key = key;
        this.statusCode = statusCode;
        this.operationType = operationType;
    }
}