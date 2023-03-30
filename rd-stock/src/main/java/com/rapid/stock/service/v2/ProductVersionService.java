package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.model.v2.ProductVersion;

public interface ProductVersionService {
    ProductVersion save(ProductVersionSaveRequest productVersionSaveRequest);
}
