package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.dto.v2.ProductVersionSaveResponse;
import com.rapid.stock.exception.NotFoundException;
import com.rapid.stock.mapper.v2.request.ProductVersionMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.ProductVersionMapperSaveResponse;
import com.rapid.stock.model.operations.GeneralDeleteOperation;
import com.rapid.stock.model.operations.GeneralSaveOperation;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.ProductVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;

@Service
@RequiredArgsConstructor
public class ProductVersionServiceImp implements ProductVersionService {

    private final ProductVersionMapperSaveRequest productVersionMapperSaveRequest;
    private final ProductVersionMapperSaveResponse productVersionMapperSaveResponse;
    private final ProductVersionRepository productVersionRepository;
    private final Validator validator;
    private final StorageImageService storageImageService;

    @Value("${cloud.aws.s3.bucket.product.template-key}")
    private final String templateKey;
    @Value("${cloud.aws.s3.bucket.product.name}")
    private final String bucketName;

    @Override
    public ProductVersionSaveResponse save(ProductVersionSaveRequest productVersionSaveRequest, MultipartFile multipartFile) {
          final ProductVersion productVersion = GeneralSaveOperation
                           .builder()
                           .mapper(productVersionMapperSaveRequest)
                           .repository(productVersionRepository)
                           .validator(validator)
                           .build()
                           .save(productVersionSaveRequest);

        String keyWithFileName = generateFullKey(productVersion);

        storageImageService.uploadImage(bucketName, keyWithFileName, multipartFile);

        return productVersionMapperSaveResponse.map(productVersion.getVersionId());
    }

    @Override
    public Page<ProductVersion> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return productVersionRepository.findAll(pageRequest);
    }

    @Override
    public void delete(Long id) {
        ProductVersion productVersion = productVersionRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("Product version ID: " + id + " was not found")
        );

        productVersionRepository.delete(productVersion);

        String keyWithFileName = generateFullKey(productVersion);

        storageImageService.deleteImage(bucketName, keyWithFileName);
    }

    private String generateFullKey(ProductVersion productVersion) {
        String key = String.format(
                templateKey,
                productVersion.getParentProduct().getCompanyId(),
                productVersion.getParentProduct().getId(),
                productVersion.getVersionId()
        );

        return key.concat(productVersion.getFilename());
    }
}
