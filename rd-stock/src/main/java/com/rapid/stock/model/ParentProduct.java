package com.rapid.stock.model;

import com.rapid.stock.model.annotations.CascadeSaveCollection;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document
public class ParentProduct{

    @Id
    private String id;
    @Indexed(unique = true)
    @NotBlank(message = "product id can't be blank")
    private String productId;
    @NotBlank(message = "name can't be blank")
    private String productName;
    @NotBlank(message = "description can't be blank")
    private String productDescription;
    @NotNull
    private LocalDateTime createdAt;
    @Valid
    private List<ProductVersion> productVersions;
    @DocumentReference
    @CascadeSaveCollection
    private List<Rack> associatedRacks;

    @Builder
    public ParentProduct(String productId, String productName, String productDescription, LocalDateTime createdAt, List<ProductVersion> productVersions, List<Rack> associatedRacks) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.createdAt = createdAt;
        this.productVersions = productVersions;
        this.associatedRacks = associatedRacks;
    }

    public ParentProduct() {
    }
}
