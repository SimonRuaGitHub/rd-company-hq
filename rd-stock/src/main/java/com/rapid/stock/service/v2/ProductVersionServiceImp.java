package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.mapper.v2.request.ProductVersionMapperSaveRequest;
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
@Profile("rational-db")
public class ProductVersionServiceImp implements ProductVersionService{

    private final ProductVersionMapperSaveRequest productVersionMapper;
    private final ProductVersionRepository productVersionRepository;
    private final Validator validator;
    private final StorageImageService storageImageService;

    @Override
    public ProductVersion save(ProductVersionSaveRequest productVersionSaveRequest, MultipartFile multipartFile) {
           storageImageService.uploadImage(multipartFile, productVersionSaveRequest.getFilename());
           return GeneralSaveOperationService
                   .builder()
                   .mapper(productVersionMapper)
                   .repository(productVersionRepository)
                   .validator(validator)
                   .build()
                   .save(productVersionSaveRequest);

    }

    @Override
    public Page<ProductVersion> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return productVersionRepository.findAll(pageRequest);
    }
}
