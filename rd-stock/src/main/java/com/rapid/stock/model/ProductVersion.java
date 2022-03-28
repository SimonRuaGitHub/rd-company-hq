package com.rapid.stock.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductVersion{

    @Id
    private String id;
    @Indexed(unique = true, sparse = true)
    @NotBlank(message = "version Id can't be blank")
    private String versionId;
    @NotBlank(message = "version name can't be blank")
    private String name;
    @NotBlank(message = "version description can't be blank")
    private String description;
    @NotNull(message = "product type can't be null")
    private ProductType productType;
    @Min(value=0, message = "price can't be less than 0")
    @NotNull(message = "price can't be null")
    private Double price;
    @Min(value=0, message = "quantity can't be less than 0")
    @NotNull(message = "quantity can't be null")
    private Integer quantityAvailable;
    private boolean isAvailable;
    @NotNull
    private LocalDateTime createdAt;
    @DocumentReference
    private List<OptionCategory> optionCategories;

    @Builder
    public ProductVersion(String versionId, String name, String description, ProductType productType, Double price, Integer quantityAvailable, boolean isAvailable, LocalDateTime createdAt, List<OptionCategory> optionCategories) {
        this.versionId = versionId;
        this.name = name;
        this.description = description;
        this.productType = productType;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.optionCategories = optionCategories;
    }

    public ProductVersion() {
    }
}
