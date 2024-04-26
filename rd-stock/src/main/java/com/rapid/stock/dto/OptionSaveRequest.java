package com.rapid.stock.dto;

import lombok.Data;

import java.util.List;

@Data
public class OptionSaveRequest {
    private int id;
    private String name;
    private Double price;
    private List<AvailabilitySaveRequest> optionAvailabilities;
}
