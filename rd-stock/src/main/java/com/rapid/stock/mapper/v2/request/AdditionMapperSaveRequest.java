package com.rapid.stock.mapper.v2.request;

import com.rapid.stock.dto.AdditionSaveRequest;
import com.rapid.stock.mapper.v2.CommonMapper;
import com.rapid.stock.model.rules.AdditionSchemaRules;
import com.rapid.stock.model.v2.Addition;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.repository.v2.AdditionRepository;
import com.rapid.stock.repository.v2.OptionCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AdditionMapperSaveRequest implements MapperRequest<Addition, AdditionSaveRequest> {

    private AdditionSchemaRules additionSchemaRules;
    private CommonMapper commonMapper;
    private OptionCategoryRepository optionCategoryRepository;

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
                .optionCategories(
                        getOptionCategories( additionSaveRequest.getAdditionMedataSaveRequest().getOptionCategoryIds() )
                )
                .build();
    }

    public List<OptionCategory> getOptionCategories(List<Long> optionCategoryIds) {
        return commonMapper.mapToEntitiesByIds(optionCategoryIds, optionCategoryRepository);
    }
}