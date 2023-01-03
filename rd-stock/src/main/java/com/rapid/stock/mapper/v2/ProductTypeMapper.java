package com.rapid.stock.mapper.v2;

import com.rapid.stock.dto.ProductTypeDTO;
import com.rapid.stock.model.v2.ProductType;

public class ProductTypeMapper {

    public ProductType mapProductType(ProductTypeDTO productTypeDTO){
           return ProductType.builder().name(productTypeDTO.getName()).build();
    }
}
