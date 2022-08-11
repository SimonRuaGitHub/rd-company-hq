package com.rapid.stock.service;

import com.rapid.stock.dto.ParentProductSaveRequest;
import com.rapid.stock.model.mongo.ParentProduct;


public interface ProductService {
    ParentProduct save(ParentProductSaveRequest parentProductDto);
}
