package com.rapid.stock.service;

import com.rapid.stock.dto.ParentProductSaveRequest;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.model.ParentProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

public interface ProductService {
    public ParentProduct save(ParentProductSaveRequest parentProductDto);
}
