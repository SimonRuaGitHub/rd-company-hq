package com.rapid.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StorageImageError {
    private String imageName;
    private String operation;
    private String bucketName;
    private String message;
}
