package com.rapid.stock.mapper.v2.response;


import com.rapid.stock.dto.v2.ProductVersionSaveResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductVersionMapperSaveResponse {
    
    public ProductVersionSaveResponse map(String versionId) {
        final UUID productVersionId = UUID.fromString(versionId);
        return ProductVersionSaveResponse.builder().versionId(productVersionId).build();
    }
}
