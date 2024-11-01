package com.rapid.stock.service.v2;

import com.rapid.stock.dto.AdditionSaveRequest;
import com.rapid.stock.dto.AdditionSaveResponse;
import com.rapid.stock.exception.NotFoundException;
import com.rapid.stock.mapper.v2.request.AdditionMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.AdditionMapperSaveResponse;
import com.rapid.stock.model.operations.GeneralSaveOperation;
import com.rapid.stock.model.v2.Addition;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.AdditionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
@RequiredArgsConstructor
public class AdditionServiceImp implements AdditionService {

    private final AdditionRepository additionRepository;
    private final AdditionMapperSaveRequest additionMapperSaveRequest;
    private final Validator validator;
    private final AdditionMapperSaveResponse additionMapperSaveResponse;
    private final StorageImageService storageImageService;

    @Value("${cloud.aws.s3.bucket.addition.template-key}")
    private String templateKey;
    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;

    @Override
    public AdditionSaveResponse save(AdditionSaveRequest additionRequestSave) {
        Addition addition = GeneralSaveOperation
                .builder()
                .mapper(additionMapperSaveRequest)
                .repository(additionRepository)
                .validator(validator)
                .build()
                .save(additionRequestSave);

        String keyWithFileName = generateFullKey(addition);

        storageImageService.uploadImage(bucketName, keyWithFileName, additionRequestSave.getImage());

        return additionMapperSaveResponse.map(addition);
    }

    @Override
    public Page<Addition> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return additionRepository.findAll(pageRequest);
    }

    @Override
    public void delete(Long additionId) {
       Addition addition = additionRepository.findById(additionId).orElseThrow(
               () -> new NotFoundException("Addition ID: " + additionId + " was not found")
       );

       addition.getOptionCategories().forEach(
               optionCategory -> addition.getOptionCategories().remove(optionCategory)
       );

       additionRepository.delete(addition);

       String keyWithFileName = generateFullKey(addition);

       storageImageService.deleteImage(bucketName, keyWithFileName);
    }

    private String generateFullKey(Addition addition) {
        String key = String.format(
                templateKey,
                addition.getCompanyId(),
                addition.getId()
        );

        return key.concat(addition.getFileName());
    }
}
