package com.rapid.stock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rapid.stock.model.v1.ProductType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class ProductVersionSaveRequest {

    private String name;
    private String description;
    private ProductType productType;
    private Double price;
    @Getter(AccessLevel.NONE)
    private boolean isAvailable;
    private List<String> optionCategoriesIds;
    private List<AvailabilityDTO> productAvailabilities;

    @JsonProperty("isAvailable")
    public boolean isAvailable(){
        return isAvailable;
    }
}
