package com.rapid.stock.mapper.v2.request;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.exception.NotValidProductException;
import com.rapid.stock.mapper.v2.CommonMapper;
import com.rapid.stock.model.rules.GeneralSchemaRules;
import com.rapid.stock.model.rules.ProductSchemaRules;
import com.rapid.stock.model.v2.*;
import com.rapid.stock.repository.v2.OptionCategoryRepository;
import com.rapid.stock.repository.v2.ProductTypeRepository;
import com.rapid.stock.repository.v2.ProductVersionRepository;
import com.rapid.stock.repository.v2.RackRepository;
import com.rapid.stock.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
@Profile("rational-db")
public class ParentProductMapperSaveRequest implements MapperRequest<ParentProduct, ParentProductSaveRequest> {

    private final CommonMapper commonMapper;
    private final Util util;
    private final ProductVersionRepository productVersionRepository;
    private final RackRepository rackRepository;
    private final ProductTypeRepository productTypeRepository;
    private final OptionCategoryRepository optionCategoryRepository;
    private final ProductSchemaRules productSchemaRules;
    private final GeneralSchemaRules generalSchemaRules;

    public ParentProduct mapToEntity(ParentProductSaveRequest productSaveRequest) {
            return ParentProduct.builder()
                    .name(getValidatedName(productSaveRequest.getProductName(), productSaveRequest.getCompanyId()))
                    .description(productSaveRequest.getProductDescription())
                    .createdAt(LocalDateTime.now())
                    .companyId(productSaveRequest.getCompanyId())
                    .productVersions(mapProductVersionsList(productSaveRequest.getProductVersionIds()))
                    .associatedRacks(mapRackList(productSaveRequest.getRackIds(), productSaveRequest.getCompanyId()))
                    .productTypes(mapProductTypesList(productSaveRequest.getTypeIds()))
                    .optionCategories( mapOptionCategoryList(productSaveRequest.getOptionCategoryIds(), productSaveRequest.getCompanyId()) )
                    .build();
    }

    private String getValidatedName(String productName, String companyId) {
            productSchemaRules.validateNoRepeatedProductNameForCompany(productName, companyId);
            return productName;
    }

    private List<ProductVersion> mapProductVersionsList(List<String> productVersionIds){
            return commonMapper.mapToEntitiesByIds(util.parseStringListToLong(productVersionIds), productVersionRepository);
    }

    private List<Rack> mapRackList(List<String> rackIds, String companyId){
        List<Rack> mappedRacks = commonMapper.mapToEntitiesByIds(util.parseStringListToLong(rackIds), rackRepository);
        generalSchemaRules.validateBusinessObjectsOfSameCompany(
                mappedRacks,
                companyId,
                () -> new NotValidProductException("Product contains at least one rack which doesn't belong to the same company"));
        return mappedRacks;
    }

    private List<ProductType> mapProductTypesList(List<String> typeIds){
        return commonMapper.mapToEntitiesByIds(util.parseStringListToLong(typeIds), productTypeRepository);
    }

    private List<OptionCategory> mapOptionCategoryList(List<String> optionCategoryIds, String companyId) {
        List<OptionCategory> mappedOptionCategories = commonMapper.mapToEntitiesByIds(util.parseStringListToLong(optionCategoryIds), optionCategoryRepository);
        return productSchemaRules.optionCategoriesOfSameCompany(mappedOptionCategories, companyId);
    }
}
