package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.v2.ProductVersionMapper;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.ProductVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Profile("rational-db")
public class ProductVersionServiceImp implements ProductVersionService{

    private final ProductVersionMapper productVersionMapper;
    private final ProductVersionRepository productVersionRepository;
    private final Validator validator;
    private final StorageImageService storageImageService;

    @Override
    public ProductVersion save(ProductVersionSaveRequest productVersionSaveRequest, MultipartFile multipartFile) {
        ProductVersion productVersion = productVersionMapper.mapSaveRequest(productVersionSaveRequest);

        Set<ConstraintViolation<Object>> violations = validator.validate(productVersion);

        if(!violations.isEmpty()){
            throw new InvalidDataFieldException("Some of the fields have invalid data or no data at all", violations);
        }

        ProductVersion savedProductVersion;

        try {
            storageImageService.uploadImage(multipartFile, productVersion.getFilename());
            savedProductVersion = productVersionRepository.save(productVersion);
        } catch (Exception ex){
            ex.printStackTrace();
            throw new SaveException("Failed to save following product version with version id: "
                    + productVersion.getVersionId());
        }

        return savedProductVersion;
    }
}
