package com.rapid.stock.mapper.v2.response;

import com.rapid.stock.dto.ProductTypeSaveResponse;
import com.rapid.stock.model.v2.ProductType;

public class ProductTypeMapperSaveResponse {
    public ProductTypeSaveResponse map(ProductType productType) {
        return  ProductTypeSaveResponse
                .builder()
                .id(productType.getId())
                .name(productType.getName())
                .build();
    }
}
