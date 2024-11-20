package com.rapid.stock.model.v2;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OPTION_CATEGORIES_PRODUCT_VERSIONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Getter
    private enum OptionType {

        BASE_PRODUCT("base_product"), PACKAGE("package"), ADDITION("addition");

        private final String value;

        OptionType(String value) {
            this.value = value;
        }
    }
}
