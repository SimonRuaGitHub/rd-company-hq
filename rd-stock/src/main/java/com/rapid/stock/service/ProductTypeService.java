package com.rapid.stock.service;

import com.rapid.stock.dto.ProductTypeDTO;
import com.rapid.stock.model.v2.ProductType;

import java.util.List;

public interface ProductTypeService {
    public ProductType save(ProductTypeDTO productTypeDTO);

    public List<String> getAllProductTypeNames();
}
