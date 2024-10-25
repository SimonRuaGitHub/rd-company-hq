package com.rapid.stock.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdditionMedataSaveRequest {
    private String name;
    private Double price;
    private String companyId;
    private List<Long> optionCategoryIds;
}
