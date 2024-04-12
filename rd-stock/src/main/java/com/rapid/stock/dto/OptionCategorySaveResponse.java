package com.rapid.stock.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OptionCategorySaveResponse {
    private Long id;
    private String name;
    private String company;
}
