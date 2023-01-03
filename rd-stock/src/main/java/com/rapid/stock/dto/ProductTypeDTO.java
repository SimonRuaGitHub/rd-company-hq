package com.rapid.stock.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductTypeDTO {
    private String name;
    private List<String> parentProductIds;
}
