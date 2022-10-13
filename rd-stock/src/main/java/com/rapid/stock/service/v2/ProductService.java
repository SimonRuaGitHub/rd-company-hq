package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.model.v2.ParentProduct;

public interface ProductService {
    ParentProduct save(ParentProductSaveRequest parentProductDto);
}
