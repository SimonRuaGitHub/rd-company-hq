package com.rapid.stock.mapper.v2;

import com.rapid.stock.dto.OptionCategoryDTO;
import com.rapid.stock.model.rules.OptionsSchemaRules;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.ProductVersionRepository;
import com.rapid.stock.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Profile("rational-db")
public class OptionCategoryMapper {

    private final Util util;
    private final MapperList mapperList;
    private final ProductVersionRepository productVersionRepository;
    private final OptionsSchemaRules optionsSchemaRules;

    public OptionCategory mapToOptionCategory(OptionCategoryDTO optionCategoryDTO){
           return OptionCategory.builder()
                               .name(getNameValidated(optionCategoryDTO.getName(), optionCategoryDTO.getCompanyId()))
                               .descrip(optionCategoryDTO.getDescription())
                               .label(getLabelValidated(optionCategoryDTO.getLabel(), optionCategoryDTO.getCompanyId()))
                               .companyId(optionCategoryDTO.getCompanyId())
                               .productVersions( mapProductVersionsList(optionCategoryDTO.getProdVersionIds()) )
                               .build();
    }

    private List<ProductVersion> mapProductVersionsList(List<String> productVersionIds){
        return mapperList.mapToEntitiesByIds(util.parseStringListToLong(productVersionIds), productVersionRepository);
    }

    private String getNameValidated(String name, String companyId) {
            return optionsSchemaRules.uniqueName(name, companyId);
    }

    private String getLabelValidated(String label, String companyId) {
           return optionsSchemaRules.uniqueLabel(label, companyId);
    }
}
