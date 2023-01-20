package com.rapid.stock.model.v2;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "OPTIONS_CATEGORIES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotBlank(message = "Option category name can't  be blank")
    private String name;
    @NotBlank(message = "Option category description can't  be blank")
    @Column(name = "description")
    private String descrip;
    @NotBlank(message = "Option category label can't  be blank")
    private String label;
    @NotBlank(message = "Option category company id can't  be blank")
    private String companyId;

    @ManyToMany(mappedBy = "optionCategories", fetch = FetchType.LAZY)
    private List<ProductVersion> productVersions;

    @Builder
    public OptionCategory(String name, String descrip, String label, String companyId , List<ProductVersion> productVersions) {
        this.name = name;
        this.descrip = descrip;
        this.label = label;
        this.companyId = companyId;
        this.productVersions = productVersions;
    }
}
