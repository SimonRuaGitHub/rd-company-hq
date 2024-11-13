package com.rapid.stock.mapper.v2.request;

import com.amazonaws.services.dynamodbv2.xspec.L;
import com.rapid.stock.dto.OptionCategorySaveRequest;
import com.rapid.stock.mapper.v2.CommonMapper;
import com.rapid.stock.model.rules.OptionsSchemaRules;
import com.rapid.stock.model.v2.Addition;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.AdditionRepository;
import com.rapid.stock.repository.v2.ParentProductRepository;
import com.rapid.stock.repository.v2.ProductVersionRepository;
import com.rapid.stock.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class OptionCategoryMapperSaveRequest implements MapperRequest<OptionCategory, OptionCategorySaveRequest> {

    private final CommonMapper commonMapper;
    private final ProductVersionRepository productVersionRepository;
    private final OptionsSchemaRules optionsSchemaRules;
    private final AdditionRepository additionRepository;

    public OptionCategory mapToEntity(OptionCategorySaveRequest optionCategoryDTO){
           return OptionCategory.builder()
                               .name(getNameValidated(optionCategoryDTO.getName(), optionCategoryDTO.getCompanyId()))
                               .descrip(optionCategoryDTO.getDescription())
                               .label(getLabelValidated(optionCategoryDTO.getLabel(), optionCategoryDTO.getCompanyId()))
                               .companyId(optionCategoryDTO.getCompanyId())
                               .productVersions(
                                       mapProductVersions(
                                           optionCategoryDTO.getProductVersionIds(),
                                           optionCategoryDTO.getCompanyId()
                                       )
                               )
                               .additions( mapAdditions( optionCategoryDTO.getAdditionIds() ) )
                               .build();
    }

    private Set<Addition> mapAdditions(List<Long> additionIds) {
        List<Addition> additions = commonMapper.mapToEntitiesByIds(additionIds, additionRepository);

        if ( additions == null || additions.isEmpty() )
            return new HashSet<>();
        else return new HashSet<>(additions);
    }

    private Set<ProductVersion> mapProductVersions(List<Long> productVersionIds, String companyId){
        List<ProductVersion> mappedProductVersions = commonMapper.mapToEntitiesByIds(
                productVersionIds,
                productVersionRepository
        );

        optionsSchemaRules.productsOfSameCompany(mappedProductVersions, companyId);

        if ( mappedProductVersions == null || mappedProductVersions.isEmpty() )
            return new HashSet<>();
        else
            return new HashSet<>(mappedProductVersions);
    }

    private String getNameValidated(String name, String companyId) {
            return optionsSchemaRules.uniqueName(name, companyId);
    }

    private String getLabelValidated(String label, String companyId) {
           return optionsSchemaRules.uniqueLabel(label, companyId);
    }
}
