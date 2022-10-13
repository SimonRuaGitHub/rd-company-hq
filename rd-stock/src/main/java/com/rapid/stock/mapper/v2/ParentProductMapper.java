package com.rapid.stock.mapper.v2;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.model.v2.ParentProduct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@Profile("rational-db")
public class ParentProductMapper {

    public ParentProduct mapSaveRequest(ParentProductSaveRequest productSaveRequest) {
            return ParentProduct.builder()
                    .productId(productSaveRequest.getProductId())
                    .name(productSaveRequest.getProductName())
                    .description(productSaveRequest.getProductDescription())
                    .createdAt(LocalDateTime.now())
                    .productVersions(null)
                    .associatedRacks(null)
                    .productTypes(null)
                    .build();
    }
}
