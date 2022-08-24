package com.rapid.stock.model.v2;

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
    private String id;
    @NotBlank(message = "Option category name can't  be blank")
    private String name;
    @NotBlank(message = "Option category description can't  be blank")
    private String descrip;
    @NotBlank(message = "Option category label can't  be blank")
    private String label;

    @Valid
    @NotEmpty(message = "List of options must not be empty")
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<ParentProduct> products;

    @Builder
    public OptionCategory(String name, String descrip, String label , List<ParentProduct> products) {
        this.name = name;
        this.descrip = descrip;
        this.label = label;
        this.products = products;
    }
}
