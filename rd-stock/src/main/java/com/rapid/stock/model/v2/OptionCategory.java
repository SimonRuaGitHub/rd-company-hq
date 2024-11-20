package com.rapid.stock.model.v2;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "OPTION_CATEGORIES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Option category name can't  be blank")
    private String name;
    @NotBlank(message = "Option category description can't  be blank")
    @Column(name = "description")
    private String descrip;
    @NotBlank(message = "Option category label can't  be blank")
    private String label;
    @NotBlank(message = "Option category company id can't  be blank")
    private String companyId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "OPTION_CATEGORIES_ADDITIONS",
            joinColumns = {
                    @JoinColumn(name = "option_category_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "addition_id", referencedColumnName = "id")
            }
    )
    @JsonBackReference
    private Set<Addition> additions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "OPTION_CATEGORIES_PRODUCT_VERSIONS",
            joinColumns = {
                    @JoinColumn(name = "option_category_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "product_version_id", referencedColumnName = "id")
            }
    )
    @JsonBackReference
    private Set<ProductVersion> productVersions = new HashSet<>();

    @OneToMany(mappedBy = "optionCategory")
    private Set<Option> options = new HashSet<>();

    @Builder
    public OptionCategory(
            String name,
            String descrip,
            String label,
            String companyId,
            Set<ProductVersion> productVersions,
            Set<Addition> additions
    ) {
        this.name = name;
        this.descrip = descrip;
        this.label = label;
        this.companyId = companyId;
        this.productVersions = productVersions;
        this.additions = additions;
    }
}
