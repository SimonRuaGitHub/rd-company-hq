package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.model.v2.ProductVersion;
import org.springframework.web.multipart.MultipartFile;

public interface ProductVersionService {
    ProductVersion save(ProductVersionSaveRequest productVersionSaveRequest, MultipartFile image);
}
