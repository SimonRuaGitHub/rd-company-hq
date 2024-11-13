package com.rapid.stock.mapper.v2.request;

import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.exception.NotValidProductVersionException;
import com.rapid.stock.mapper.v2.CommonMapper;
import com.rapid.stock.model.rules.ProductVersionSchemaRules;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.OptionCategoryRepository;
import com.rapid.stock.repository.v2.ParentProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ProductVersionMapperSaveRequest implements MapperRequest<ProductVersion, ProductVersionSaveRequest> {

    private final CommonMapper commonMapper;
    private final ParentProductRepository parentProductRepository;
    private final ProductVersionSchemaRules productVersionSchemaRules;
    private final OptionCategoryRepository optionCategoryRepository;

    public ProductVersion mapToEntity(ProductVersionSaveRequest productVersionSaveRequest) {

           ParentProduct parentProduct = getParentProduct(productVersionSaveRequest.getParentProductId());

           if (productVersionSaveRequest.isAvailable() &&
               productVersionSchemaRules.areThereMoreProductVersionsAvailable(parentProduct))
               throw new NotValidProductVersionException("There can only be one product version available per parent product");

           return ProductVersion
                   .builder()
                   .versionId(UUID.randomUUID().toString())
                   .name(productVersionSaveRequest.getName())
                   .description(productVersionSaveRequest.getDescription())
                   .createdAt(LocalDateTime.now())
                   .price(productVersionSaveRequest.getPrice())
                   .isAvailable(productVersionSaveRequest.isAvailable())
                   .filename(productVersionSaveRequest.getFilename())
                   .parentProduct(parentProduct)
                   .optionCategories(getOptionCategories( productVersionSaveRequest.getOptionCategoryIds() ))
                   .build();
    }

    private ParentProduct getParentProduct(Long parentProductId){
        return commonMapper.mapToEntityById(parentProductId, parentProductRepository);
    }

    public Set<OptionCategory> getOptionCategories(List<Long> optionCategoryIds) {
        List<OptionCategory> optionCategories = commonMapper.mapToEntitiesByIds(
                optionCategoryIds,
                optionCategoryRepository
        );

        if ( optionCategories == null || optionCategories.isEmpty() )
            return new HashSet<>();
        else return new HashSet<>(optionCategories);
    }
}
