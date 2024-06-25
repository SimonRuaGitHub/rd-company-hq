package com.rapid.stock.model.v2;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PARENT_PRODUCTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParentProduct{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name can't be blank")
    private String name;

    @NotBlank(message = "description can't be blank")
    private String description;

    @NotNull
    private LocalDateTime createdAt;

    @NotBlank(message = "Company id can't be blank")
    private String companyId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonManagedReference
    private List<ProductVersion> productVersions;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCTS_RACKS",
        joinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id"),
        },
        inverseJoinColumns = {
            @JoinColumn(name = "rack_id", referencedColumnName = "id")
       }
    )
    @JsonManagedReference
    private List<Rack> associatedRacks;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name= "PARENT_PRODUCTS_TYPES",
            joinColumns = {
                    @JoinColumn(name = "parent_product_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name= "type_id", referencedColumnName = "id")
            }
    )
    @JsonManagedReference
    private List<ProductType> productTypes;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCTS_OPTION_CATEGORIES",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "id")
            }
    )
    @JsonManagedReference
    private List<OptionCategory> optionCategories;

    @Builder
    public ParentProduct(String name, String description, LocalDateTime createdAt, List<ProductVersion> productVersions, List<Rack> associatedRacks, List<ProductType> productTypes, String companyId, List<OptionCategory> optionCategories) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.productVersions = productVersions;
        this.associatedRacks = associatedRacks;
        this.productTypes = productTypes;
        this.companyId = companyId;
        this.optionCategories = optionCategories;
    }
}
