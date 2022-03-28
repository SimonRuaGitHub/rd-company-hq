package com.rappid.company.dto;

import lombok.Data;

import java.util.List;

@Data
public class OptionCategorySaveRequest {
    private String name;
    private String description;
    private String label;
    private List<OptionSaveRequest> options;
}
