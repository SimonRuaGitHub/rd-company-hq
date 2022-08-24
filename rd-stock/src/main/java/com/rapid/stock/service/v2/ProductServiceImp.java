package com.rapid.stock.service.v2;

import com.rapid.stock.dto.ParentProductSaveRequest;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.repository.v2.ParentProductRepository;
import com.rapid.stock.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Profile("rational-db")
public class ProductServiceImp implements ProductService {

    private final ParentProductRepository productRepository;

    @Override
    public ParentProduct save(ParentProductSaveRequest parentProductDto) {
         return ParentProduct.builder().build();
    }
}
