package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.dto.v2.ProductVersionSaveResponse;
import com.rapid.stock.mapper.v2.request.ProductVersionMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.ProductVersionMapperSaveResponse;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.ProductVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;

@Service
@RequiredArgsConstructor
public class ProductVersionServiceImp implements ProductVersionService{

    private final ProductVersionMapperSaveRequest productVersionMapperSaveRequest;
    private final ProductVersionMapperSaveResponse productVersionMapperSaveResponse;
    private final ProductVersionRepository productVersionRepository;
    private final Validator validator;
    private final StorageImageService storageImageService;

    @Override
    public ProductVersionSaveResponse save(ProductVersionSaveRequest productVersionSaveRequest, MultipartFile multipartFile) {
          storageImageService.uploadImage(multipartFile, productVersionSaveRequest.getFilename());
          final ProductVersion productVersion = GeneralSaveOperationService
                           .builder()
                           .mapper(productVersionMapperSaveRequest)
                           .repository(productVersionRepository)
                           .validator(validator)
                           .build()
                           .save(productVersionSaveRequest);


        return productVersionMapperSaveResponse.map(productVersion.getVersionId());
    }

    @Override
    public Page<ProductVersion> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return productVersionRepository.findAll(pageRequest);
    }
}
