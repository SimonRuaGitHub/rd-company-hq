package com.rapid.stock.dto.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class ProductVersionSaveRequest {

    private String name;
    private String description;
    private Double price;
    @Getter(AccessLevel.NONE)
    private boolean isAvailable;
    private List<OptionProductVersion> options;

    @JsonProperty("isAvailable")
    public boolean isAvailable(){
        return isAvailable;
    }
}
