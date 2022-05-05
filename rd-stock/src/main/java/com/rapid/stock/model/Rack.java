package com.rapid.stock.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document
@Data
public class Rack {
    @Id
    private String id;
    @NotBlank(message = "rack should have some name")
    private String name;
    private String description;
    @DocumentReference
    @NotNull(message = "rack should contain some product(s)")
    private List<ParentProduct> products;
    @DocumentReference
    private List<Rack> racks;
    @NotBlank(message = "companyId can't be empty")
    private String companyId;

    @Builder
    public Rack(String name, String description, List<ParentProduct> products, String companyId) {
            this.name = name;
            this.description = description;
            this.products = products;
            this.companyId = companyId;
    }

    @Builder(builderMethodName = "builderWithSubRacks")
    public Rack(String name, String description, List<ParentProduct> products, List<Rack> rackList, String companyId) {
        this.name = name;
        this.description = description;
        this.products = products;
        this.racks = rackList;
        this.companyId = companyId;
    }
}
