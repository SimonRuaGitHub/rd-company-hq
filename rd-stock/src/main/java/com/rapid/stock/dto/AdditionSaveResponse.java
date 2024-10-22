package com.rapid.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AdditionSaveResponse {
    private Long id;
    private String name;
    private Double price;
    private String fileName;
    private String companyId;
}
