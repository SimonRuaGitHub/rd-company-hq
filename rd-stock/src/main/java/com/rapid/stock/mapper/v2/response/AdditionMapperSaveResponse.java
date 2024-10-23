package com.rapid.stock.mapper.v2.response;

import com.rapid.stock.dto.AdditionSaveResponse;
import com.rapid.stock.model.v2.Addition;
import org.springframework.stereotype.Component;

@Component
public class AdditionMapperSaveResponse {

    public AdditionSaveResponse map(Addition addition) {
        return AdditionSaveResponse.builder()
                .id(addition.getId())
                .name(addition.getName())
                .price(addition.getPrice())
                .fileName(addition.getFileName())
                .companyId(addition.getCompanyId())
                .build();
    }
}
