package com.rapid.stock.mapper.v2;

import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.exception.NotValidProductVersionException;
import com.rapid.stock.model.rules.ProductVersionSchemaRules;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.ParentProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@AllArgsConstructor
@Profile("rational-db")
public class ProductVersionMapper {

    private final CommonMapper commonMapper;
    private final ParentProductRepository parentProductRepository;
    private final ProductVersionSchemaRules productVersionSchemaRules;

    public ProductVersion mapSaveRequest(ProductVersionSaveRequest productVersionSaveRequest) {

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
                   .build();
    }

    private ParentProduct getParentProduct(Long parentProductId){
        return commonMapper.mapToEntityById(parentProductId, parentProductRepository);
    }
}
