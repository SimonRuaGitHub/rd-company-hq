package com.rapid.stock.mapper.v2.request;

import com.rapid.stock.dto.AdditionSaveRequest;
import com.rapid.stock.model.v2.Addition;

public class AdditionMapperSaveRequest implements MapperRequest<Addition, AdditionSaveRequest> {

    @Override
    public Addition mapToEntity(AdditionSaveRequest additionSaveRequest) {
        return Addition.builder()
                .name(additionSaveRequest.getName())
                .price(additionSaveRequest.getPrice())
                .build();
    }
}
