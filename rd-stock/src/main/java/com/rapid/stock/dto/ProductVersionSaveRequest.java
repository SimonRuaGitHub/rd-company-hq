package com.rapid.stock.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rapid.stock.model.ProductType;
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
    private Integer quantityAvailable;
    @Getter(AccessLevel.NONE)
    private boolean isAvailable;
    private List<String> optionCategoriesIds;

    @JsonProperty("isAvailable")
    public boolean isAvailable(){
        return isAvailable;
    }
}
