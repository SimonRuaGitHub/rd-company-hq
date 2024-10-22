package com.rapid.stock.mapper.v2.request;

import com.rapid.stock.dto.AdditionSaveRequest;
import com.rapid.stock.model.v2.Addition;

public class AdditionMapperSaveRequest implements MapperRequest<Addition, AdditionSaveRequest> {

    @Override
    public Addition mapToEntity(AdditionSaveRequest additionMetadataSaveRequest) {
        return Addition.builder()
                .name(additionMetadataSaveRequest.getAdditionMedataSaveRequest().getName())
                .price(additionMetadataSaveRequest.getAdditionMedataSaveRequest().getPrice())
                .fileName(additionMetadataSaveRequest.getImage().getName())
                .companyId(additionMetadataSaveRequest.getAdditionMedataSaveRequest().getCompanyId())
                .build();
    }
}