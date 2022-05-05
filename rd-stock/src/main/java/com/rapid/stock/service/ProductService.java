package com.rapid.stock.service;

import com.rapid.stock.dto.ParentProductSaveRequest;
import com.rapid.stock.model.ParentProduct;
import com.rapid.stock.model.Rack;


public interface ProductService {
    ParentProduct save(ParentProductSaveRequest parentProductDto);
}
