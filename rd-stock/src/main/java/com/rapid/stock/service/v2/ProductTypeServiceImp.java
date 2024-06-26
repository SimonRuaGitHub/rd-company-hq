package com.rapid.stock.service.v2;

import com.rapid.stock.dto.ProductTypeSaveRequest;
import com.rapid.stock.dto.ProductTypeSaveResponse;
import com.rapid.stock.exception.NotFoundException;
import com.rapid.stock.mapper.v2.request.ProductTypeMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.ProductTypeMapperSaveResponse;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductType;
import com.rapid.stock.repository.v2.ProductTypeRepository;
import com.rapid.stock.service.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductTypeServiceImp implements ProductTypeService {

    private ProductTypeMapperSaveRequest productTypeMapperSaveRequest;
    private ProductTypeMapperSaveResponse productTypeMapperSaveResponse;
    private ProductTypeRepository productTypeRepository;
    private Validator validator;

    @Override
    public ProductTypeSaveResponse save(ProductTypeSaveRequest productTypeSaveRequest) {
        ProductType productType = GeneralSaveOperationService
                .builder()
                .mapper(productTypeMapperSaveRequest)
                .repository(productTypeRepository)
                .validator(validator)
                .build()
                .save(productTypeSaveRequest);

        return productTypeMapperSaveResponse.map(productType);
    }

    @Override
    public List<String> getAllProductTypeNames() {
        return productTypeRepository
                .findAll()
                .stream()
                .map(ProductType::getName)
                .collect(
                        Collectors.toList()
                );
    }

    @Override
    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    @Override
    public void deleteType(Long typeId) throws Throwable {
        Supplier<RuntimeException> exceptionSupplier =
                () -> new NotFoundException("Type ID: " + typeId + " was not found");

        GeneralDeleteOperationService deleteOperationService = GeneralDeleteOperationService
                .builder()
                .repository(productTypeRepository)
                .exceptionSupplier(exceptionSupplier)
                .build();

        deleteOperationService.delete(typeId);
    }
}
