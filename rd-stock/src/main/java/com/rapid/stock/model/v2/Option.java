package com.rapid.stock.model.v2;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OPTION_CATEGORIES_PRODUCT_VERSIONS")
@Getter
@Setter
@NoArgsConstructor
//TODO 21-NOV-2024: see if the many-to-many represented with options it's set up properly
public class Option {

    @EmbeddedId
    private OptionKey id;

    @ManyToOne
    @MapsId("productVersionId")
    @JoinColumn(name = "product_version_id")
    private ProductVersion productVersion;

    @ManyToOne
    @MapsId("optionCategoryId")
    @JoinColumn(name = "option_category_id")
    private OptionCategory optionCategory;

    @Enumerated(EnumType.STRING)
    private OptionType optionType;

    @Builder
    public Option(OptionKey id, ProductVersion productVersion, OptionCategory optionCategory, OptionType optionType) {
        this.id = id;
        this.productVersion = productVersion;
        this.optionCategory = optionCategory;
        this.optionType = optionType;
    }
}
