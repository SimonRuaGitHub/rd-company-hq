package com.rapid.stock.model.rules;


import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.ProductVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductVersionSchemaRules {
    private final ProductVersionRepository pvRepository;

    public boolean areThereMoreProductVersionsAvailable(ParentProduct parentProduct) {
          List<ProductVersion> productVersions = pvRepository.findByParentProduct(parentProduct);
          return productVersions.stream().anyMatch(pv -> pv.isAvailable());
    }
}
