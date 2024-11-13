package com.rapid.stock.model.v2;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PRODUCT_VERSIONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVersion{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotBlank(message = "filename can't be blank")
    private String filename;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonBackReference
    private ParentProduct parentProduct;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_versions_id", referencedColumnName = "id")
    @JsonManagedReference
    private List<Availability> productAvailabilities;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "OPTION_CATEGORIES_PRODUCT_VERSIONS",
            joinColumns = {
                    @JoinColumn(name = "product_version_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "option_category_id", referencedColumnName = "id")
            }
    )
    @JsonManagedReference
    private Set<OptionCategory> optionCategories = new HashSet<>();

    @Builder
    public ProductVersion(
            String versionId,
            String name,
            String description,
            Double price,
            boolean isAvailable,
            LocalDateTime createdAt,
            ParentProduct parentProduct,
            String filename,
            Set<OptionCategory> optionCategories
    ) {
        this.versionId = versionId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.createdAt = createdAt;
        this.parentProduct = parentProduct;
        this.filename = filename;
        this.optionCategories = optionCategories;
    }
}
