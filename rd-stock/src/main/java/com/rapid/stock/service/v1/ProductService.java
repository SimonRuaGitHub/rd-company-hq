package com.rapid.stock.service.v1;

import com.rapid.stock.dto.v1.ParentProductSaveRequest;

public interface ProductService<T> {
    T save(ParentProductSaveRequest parentProductDto);
}
