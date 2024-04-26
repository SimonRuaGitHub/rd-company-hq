package com.rapid.stock.dto;

import lombok.Data;

@Data
public class AvailabilitySaveRequest {
    private String companySiteID;
    private Integer quantityAvailable;
    private Long productVersionId;
}
