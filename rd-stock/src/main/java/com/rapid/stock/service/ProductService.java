package com.rapid.stock.service;

import com.rapid.stock.dto.ParentProductSaveRequest;

public interface ProductService<T> {
    T save(ParentProductSaveRequest parentProductDto);
}
