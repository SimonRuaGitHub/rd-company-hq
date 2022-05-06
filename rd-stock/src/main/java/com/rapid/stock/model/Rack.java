package com.rapid.stock.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotBlank;
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
    private List<ParentProduct> products;
    @DocumentReference
    private List<Rack> racks;
    @NotBlank(message = "companyId can't be empty")
    private String companyId;

    @Builder
    public Rack(String name, String description, List<ParentProduct> products, List<Rack> racks, String companyId) {
        this.name = name;
        this.description = description;
        this.products = products;
        this.racks = racks;
        this.companyId = companyId;
    }


    public Rack() {
    }
}
