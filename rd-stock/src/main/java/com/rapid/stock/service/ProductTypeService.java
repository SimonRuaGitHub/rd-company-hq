package com.rapid.stock.service;

import com.rapid.stock.dto.ProductTypeSaveRequest;
import com.rapid.stock.dto.ProductTypeSaveResponse;
import com.rapid.stock.model.v2.ProductType;

import java.util.List;

public interface ProductTypeService {
    public ProductTypeSaveResponse save(ProductTypeSaveRequest productTypeSaveRequest);

    public List<String> getAllProductTypeNames();

    public List<ProductType> getAllProductTypes();
}
