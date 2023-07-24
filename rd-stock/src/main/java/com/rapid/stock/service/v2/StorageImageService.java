package com.rapid.stock.service.v2;

import org.springframework.web.multipart.MultipartFile;

public interface StorageImageService {
    public String uploadImage(MultipartFile file, String filename);
}
