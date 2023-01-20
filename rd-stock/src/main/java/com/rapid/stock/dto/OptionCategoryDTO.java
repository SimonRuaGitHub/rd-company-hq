package com.rapid.stock.dto;

import lombok.Data;

import java.util.List;

@Data
public class OptionCategoryDTO {
    private String name;
    private String description;
    private String label;
    private String companyId;
    private List<String> prodVersionIds;
}
