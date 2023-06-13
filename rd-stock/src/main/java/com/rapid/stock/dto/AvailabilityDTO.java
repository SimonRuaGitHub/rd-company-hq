package com.rapid.stock.dto;

import lombok.Data;

@Data
public class AvailabilityDTO {
    private String companySiteID;
    private Integer quantityAvailable;
    private Long productVersionId;
}
