package com.rapid.stock.model.v2;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name="PRODUCT_TYPES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name can't be blank")
    @Pattern(regexp = "^[^0-9]+$")
    private String name;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name= "PARENT_PRODUCTS_TYPES",
            inverseJoinColumns = {
                    @JoinColumn(name = "parent_product_id", referencedColumnName = "id")
            },
            joinColumns = {
                    @JoinColumn(name= "type_id", referencedColumnName = "id")
            }
    )
    @JsonBackReference
    private List<ParentProduct> parentProducts;

    @Builder
    public ProductType(String name, List<ParentProduct> parentProducts) {
        this.name = name;
        this.parentProducts = parentProducts;
    }
}
