package com.rapid.stock.dto;

import lombok.Data;

@Data
public class ProductAvailabilityDTO {
    private String companySiteID;
    private Integer quantityAvailable;
}
