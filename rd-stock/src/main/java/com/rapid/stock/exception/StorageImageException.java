package com.rapid.stock.exception;

import com.amazonaws.AmazonServiceException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
public class StorageImageException extends AmazonServiceException {

    private final String bucket;
    private final String key;
    private final int statusCode;
    private final StorageOperationType operationType;

    public StorageImageException(
            String message,
            String bucket,
            String key,
            int statusCode,
            StorageOperationType operationType
    ) {
        super(message);
        this.bucket = bucket;
        this.key = key;
        this.statusCode = statusCode;
        this.operationType = operationType;
    }

    public enum StorageOperationType {
        READ,
        DELETE,
        PUT,
    }
}