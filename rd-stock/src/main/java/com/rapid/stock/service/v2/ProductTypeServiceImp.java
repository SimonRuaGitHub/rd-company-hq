package com.rapid.stock.service.v2;

import com.rapid.stock.dto.ProductTypeDTO;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.v2.ProductTypeMapper;
import com.rapid.stock.model.v2.ProductType;
import com.rapid.stock.repository.v2.ProductTypeRepository;
import com.rapid.stock.service.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Profile("rational-db")
public class ProductTypeServiceImp implements ProductTypeService {

    private ProductTypeMapper productTypeMapper;
    private ProductTypeRepository productTypeRepository;
    private Validator validator;

    @Override
    public ProductType save(ProductTypeDTO productTypeDTO) {
        return GeneralSaveOperationService
                .builder()
                .mapper(productTypeMapper)
                .repository(productTypeRepository)
                .validator(validator)
                .build()
                .save(productTypeDTO);
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
}
