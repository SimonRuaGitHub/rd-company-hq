package com.rapid.stock.dto;

import lombok.Data;

@Data
public class OptionSaveRequest {
    private int id;
    private String name;
    private Double price;
}
