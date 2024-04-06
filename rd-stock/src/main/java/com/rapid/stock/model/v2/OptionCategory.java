package com.rapid.stock.model.v2;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCTS_OPTION_CATEGORIES",
            joinColumns = {
                    @JoinColumn(name = "category_id", referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id")
            }
    )
    @JsonBackReference
    private List<ParentProduct> parentProducts;

    @Builder
    public OptionCategory(String name, String descrip, String label, String companyId , List<ParentProduct> parentProducts) {
        this.name = name;
        this.descrip = descrip;
        this.label = label;
        this.companyId = companyId;
        this.parentProducts = parentProducts;
    }
}
