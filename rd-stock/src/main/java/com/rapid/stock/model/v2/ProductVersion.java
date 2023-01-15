package com.rapid.stock.model.v2;

import lombok.*;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PRODUCT_VERSIONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Profile("rational-db")
public class ProductVersion{

    @Id
    private Long id;
    //Define Index
    @NotBlank(message = "version Id can't be blank")
    private String versionId;

    @NotBlank(message = "version name can't be blank")
    private String name;

    @NotBlank(message = "version description can't be blank")
    private String description;

    @Min(value=0, message = "price can't be less than 0")
    @NotNull(message = "price can't be null")
    private Double price;

    private boolean isAvailable;

    @NotNull
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private ParentProduct parentProduct;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCTS_OPTION_CATEGORIES",
            joinColumns = {
                    @JoinColumn(name = "product_version_id", referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "id")
            }
    )
    private List<OptionCategory> optionCategories;

    @Builder
    public ProductVersion(String versionId, String name, String description, Double price, boolean isAvailable, LocalDateTime createdAt, List<OptionCategory> optionCategories) {
        this.versionId = versionId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.optionCategories = optionCategories;
    }
}
