package com.rapid.stock.mapper.v2;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductType;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.model.v2.Rack;
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
public class ParentProductMapper {

    private final MapperList mapperList;
    private final Util util;
    private final ProductVersionRepository productVersionRepository;
    private final RackRepository rackRepository;
    private final ProductTypeRepository productTypeRepository;

    public ParentProduct mapSaveRequest(ParentProductSaveRequest productSaveRequest) {
            return ParentProduct.builder()
                    .name(productSaveRequest.getProductName())
                    .description(productSaveRequest.getProductDescription())
                    .createdAt(LocalDateTime.now())
                    .companyId(productSaveRequest.getCompanyId())
                    .productVersions(mapProductVersionsList(productSaveRequest.getProductVersionIds()))
                    .associatedRacks(mapRackList(productSaveRequest.getRackIds()))
                    .productTypes(mapProductTypesList(productSaveRequest.getTypeIds()))
                    .build();
    }

    private List<ProductVersion> mapProductVersionsList(List<String> productVersionIds){
            return mapperList.mapToEntitiesByIds(util.parseStringListToLong(productVersionIds), productVersionRepository);
    }

    private List<Rack> mapRackList(List<String> rackIds){
        return mapperList.mapToEntitiesByIds(util.parseStringListToLong(rackIds), rackRepository);
    }

    private List<ProductType> mapProductTypesList(List<String> typeIds){
        return mapperList.mapToEntitiesByIds(util.parseStringListToLong(typeIds), productTypeRepository);
    }
}
