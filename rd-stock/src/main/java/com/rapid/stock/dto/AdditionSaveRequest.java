package com.rapid.stock.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AdditionSaveRequest {
    private String name;
    private Double price;
}
