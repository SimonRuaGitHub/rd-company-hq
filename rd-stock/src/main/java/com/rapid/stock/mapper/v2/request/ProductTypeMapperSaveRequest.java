package com.rapid.stock.mapper.v2.request;

import com.rapid.stock.dto.ProductTypeSaveRequest;
import com.rapid.stock.mapper.v2.CommonMapper;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductType;
import com.rapid.stock.repository.v2.ParentProductRepository;
import com.rapid.stock.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductTypeMapperSaveRequest implements MapperRequest<ProductType, ProductTypeSaveRequest> {

    private final CommonMapper commonMapper;
    private final Util util;
    private final ParentProductRepository parentProductRepository;

    public ProductType mapToEntity(ProductTypeSaveRequest productTypeSaveRequest){
           return ProductType.builder()
                             .name(productTypeSaveRequest.getName())
                             .parentProducts(getParentProducts(productTypeSaveRequest.getParentProductIds()))
                             .build();
    }

    private List<ParentProduct> getParentProducts(List<String> parentProductIds){
        return commonMapper.mapToEntitiesByIds(util.parseStringListToLong(parentProductIds), parentProductRepository);
    }
}
