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
    @GeneratedValue
    private Long id;
    //Add as indexed inside db
    @NotBlank(message = "product id can't be blank")
    private String productId;
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

    @Builder
    public ParentProduct(String productId, String name, String description, LocalDateTime createdAt, List<ProductVersion> productVersions, List<Rack> associatedRacks) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.productVersions = productVersions;
        this.associatedRacks = associatedRacks;
    }
}
