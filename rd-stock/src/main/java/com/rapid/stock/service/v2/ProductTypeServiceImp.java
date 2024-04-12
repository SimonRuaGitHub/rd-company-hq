package com.rapid.stock.service.v2;

import com.rapid.stock.dto.ProductTypeSaveRequest;
import com.rapid.stock.mapper.v2.request.ProductTypeMapperSaveRequest;
import com.rapid.stock.model.v2.ProductType;
import com.rapid.stock.repository.v2.ProductTypeRepository;
import com.rapid.stock.service.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Profile("rational-db")
public class ProductTypeServiceImp implements ProductTypeService {

    private ProductTypeMapperSaveRequest productTypeMapper;
    private ProductTypeRepository productTypeRepository;
    private Validator validator;

    @Override
    public ProductType save(ProductTypeSaveRequest productTypeSaveRequest) {
        return GeneralSaveOperationService
                .builder()
                .mapper(productTypeMapper)
                .repository(productTypeRepository)
                .validator(validator)
                .build()
                .save(productTypeSaveRequest);
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
}
