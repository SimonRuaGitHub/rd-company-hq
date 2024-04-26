package com.rapid.stock.mapper.v2.response;


import com.rapid.stock.dto.v2.ParentProductSaveResponse;
import com.rapid.stock.model.v2.ParentProduct;
import org.springframework.stereotype.Component;

@Component
public class ParentProductMapperSaveResponse {
    public ParentProductSaveResponse map(ParentProduct parentProduct) {
        return ParentProductSaveResponse
                .builder()
                .id(parentProduct.getId())
                .name(parentProduct.getName())
                .build();
    }
}
