package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.dto.v2.ParentProductSaveResponse;
import com.rapid.stock.model.v2.ParentProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ParentProductSaveResponse save(ParentProductSaveRequest parentProductDto);

    Page<ParentProduct> getAll(int page, int size);
}
