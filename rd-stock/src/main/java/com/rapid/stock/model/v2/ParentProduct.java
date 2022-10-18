package com.rapid.stock.model.v2;

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
@Profile("rational-db")
public class ParentProduct{

    @Id
    private Long id;

    @NotBlank(message = "name can't be blank")
    private String name;

    @NotBlank(message = "description can't be blank")
    private String description;

    @NotNull
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<ProductVersion> productVersions;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_RACKS",
        joinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id"),
        },
        inverseJoinColumns = {
            @JoinColumn(name = "rack_id", referencedColumnName = "id")
       }
    )
    private List<Rack> associatedRacks;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCTS_OPTION_CATEGORIES",
            joinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "id")
            }
    )
    private List<OptionCategory> categories;

    @ManyToMany
    @JoinTable(name="PARENT_PRODUCTS_TYPES",
            joinColumns = {
                    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name= "type_id", referencedColumnName = "id")
            }
    )
    private List<ProductType> productTypes;

    private String companyId;

    @Builder
    public ParentProduct(Long id, String name, String description, LocalDateTime createdAt, List<ProductVersion> productVersions, List<Rack> associatedRacks, List<ProductType> productTypes, String companyId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.productVersions = productVersions;
        this.associatedRacks = associatedRacks;
        this.productTypes = productTypes;
        this.companyId = companyId;
    }


}
