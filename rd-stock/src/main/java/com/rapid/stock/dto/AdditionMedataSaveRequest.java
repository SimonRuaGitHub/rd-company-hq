package com.rapid.stock.dto;

import lombok.Data;

@Data
public class AdditionMedataSaveRequest {
    private String name;
    private Double price;
    private String companyId;
}
