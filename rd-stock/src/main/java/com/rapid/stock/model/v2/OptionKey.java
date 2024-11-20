package com.rapid.stock.model.v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionKey implements Serializable {

    @Column(name = "product_version_id")
    private Long productVersionId;
    @Column(name = "option_category_id")
    private Long optionCategoryId;

}
