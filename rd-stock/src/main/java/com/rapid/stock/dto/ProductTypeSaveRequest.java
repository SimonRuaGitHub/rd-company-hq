package com.rapid.stock.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductTypeSaveRequest {
    private String name;
    private List<String> parentProductIds;
}
