package com.rapid.stock.model.v2;

import lombok.*;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name="PRODUCT_TYPES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Profile("rational-db")
public class ProductType {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "name can't be blank")
    private String name;

    @ManyToMany(mappedBy = "productTypes", fetch = FetchType.LAZY)
    private List<ParentProduct> parentProducts;

    @Builder
    public ProductType(String name, List<ParentProduct> parentProducts) {
        this.name = name;
        this.parentProducts = parentProducts;
    }
}
