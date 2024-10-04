package com.rapid.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class S3ErrorOperation {
    private int statusCode;
    private String message;
    private String bucket;
    private String key;
    private String operation;
}
