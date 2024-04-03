package com.rapid.stock.mapper.v2.request;

import com.rapid.stock.dto.OptionCategoryDTO;
import com.rapid.stock.mapper.v2.CommonMapper;
import com.rapid.stock.model.rules.OptionsSchemaRules;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.repository.v2.ParentProductRepository;
import com.rapid.stock.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Profile("rational-db")
public class OptionCategoryMapperSaveRequest implements MapperRequest<OptionCategory, OptionCategoryDTO> {

    private final Util util;
    private final CommonMapper commonMapper;
    private final ParentProductRepository productRepository;
    private final OptionsSchemaRules optionsSchemaRules;

    public OptionCategory mapToEntity(OptionCategoryDTO optionCategoryDTO){
           return OptionCategory.builder()
                               .name(getNameValidated(optionCategoryDTO.getName(), optionCategoryDTO.getCompanyId()))
                               .descrip(optionCategoryDTO.getDescription())
                               .label(getLabelValidated(optionCategoryDTO.getLabel(), optionCategoryDTO.getCompanyId()))
                               .companyId(optionCategoryDTO.getCompanyId())
                               .parentProducts( mapProductList(optionCategoryDTO.getProductIds(), optionCategoryDTO.getCompanyId()) )
                               .build();
    }

    private List<ParentProduct> mapProductList(List<String> productIds, String companyId){
        List<ParentProduct> mappedProducts = commonMapper.mapToEntitiesByIds(util.parseStringListToLong(productIds), productRepository);
        return optionsSchemaRules.productsOfSameCompany(mappedProducts, companyId);
    }

    private String getNameValidated(String name, String companyId) {
            return optionsSchemaRules.uniqueName(name, companyId);
    }

    private String getLabelValidated(String label, String companyId) {
           return optionsSchemaRules.uniqueLabel(label, companyId);
    }
}
