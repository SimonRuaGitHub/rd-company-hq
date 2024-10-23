package com.rapid.stock.mapper.v2.request;

import com.rapid.stock.dto.AdditionSaveRequest;
import com.rapid.stock.model.rules.AdditionSchemaRules;
import com.rapid.stock.model.v2.Addition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdditionMapperSaveRequest implements MapperRequest<Addition, AdditionSaveRequest> {

    private AdditionSchemaRules additionSchemaRules;

    @Override
    public Addition mapToEntity(AdditionSaveRequest additionSaveRequest) {

        String name = additionSaveRequest.getAdditionMedataSaveRequest().getName();
        String companyId = additionSaveRequest.getAdditionMedataSaveRequest().getCompanyId();

        additionSchemaRules.validateNameAlreadyExistsForCompany(
                name,
                companyId
        );

        return Addition.builder()
                .name(additionSaveRequest.getAdditionMedataSaveRequest().getName())
                .price(additionSaveRequest.getAdditionMedataSaveRequest().getPrice())
                .fileName(additionSaveRequest.getImage().getName())
                .companyId(additionSaveRequest.getAdditionMedataSaveRequest().getCompanyId())
                .build();
    }
}