package com.rapid.stock.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductTypeSaveResponse {
    private Long id;
    private String name;
}
