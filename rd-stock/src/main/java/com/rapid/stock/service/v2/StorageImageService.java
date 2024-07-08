package com.rapid.stock.service.v2;

import org.springframework.web.multipart.MultipartFile;

public interface StorageImageService {
    public String uploadImage(String bucketName,  String key, MultipartFile multipartFile);
    public void deleteImage(String bucketName, String key);
}
