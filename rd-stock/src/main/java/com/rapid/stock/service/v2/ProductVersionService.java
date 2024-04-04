package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.dto.v2.ProductVersionSaveResponse;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductVersion;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProductVersionService {
    ProductVersionSaveResponse save(ProductVersionSaveRequest productVersionSaveRequest, MultipartFile image);

    Page<ProductVersion> getAll(int page, int size);
}
