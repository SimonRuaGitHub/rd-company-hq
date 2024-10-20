package com.rapid.stock.model.v2;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "ADDITIONS")
@Getter
@Setter
@NoArgsConstructor
public class Addition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name can't be empty")
    private String name;

    @Min(value=0, message = "price can't be less than 0")
    @NotNull(message = "price can't be null")
    private Double price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "OPTION_CATEGORIES_ADDITIONS",
            joinColumns = {
                    @JoinColumn(name = "addition_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "option_category", referencedColumnName = "id")
            }
    )
    @JsonManagedReference
    private List<OptionCategory> optionCategories;

    @Builder
    public Addition(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
