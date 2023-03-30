package com.rapid.stock.mapper.v2;

import com.rapid.stock.dto.ProductTypeDTO;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductType;
import com.rapid.stock.repository.v2.ProductTypeRepository;
import com.rapid.stock.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Profile("rational-db")
public class ProductTypeMapper {

    private final CommonMapper commonMapper;
    private final Util util;
    private final ProductTypeRepository productTypeRepository;

    public ProductType mapProductType(ProductTypeDTO productTypeDTO){
           return ProductType.builder()
                             .name(productTypeDTO.getName())
                             .parentProducts(getParentProducts(productTypeDTO.getParentProductIds()))
                             .build();
    }

    private List<ParentProduct> getParentProducts(List<String> parentProductIds){
        return commonMapper.mapToEntitiesByIds(util.parseStringListToLong(parentProductIds), productTypeRepository);
    }
}
