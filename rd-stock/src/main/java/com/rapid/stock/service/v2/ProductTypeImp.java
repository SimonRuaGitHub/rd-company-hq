package com.rapid.stock.service.v2;

import com.rapid.stock.dto.ProductTypeDTO;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.v2.ProductTypeMapper;
import com.rapid.stock.model.v2.ProductType;
import com.rapid.stock.model.v2.Rack;
import com.rapid.stock.repository.v2.ProductTypeRepository;
import com.rapid.stock.service.ProductTypeService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ProductTypeImp implements ProductTypeService {

    private ProductTypeMapper productTypeMapper;
    private ProductTypeRepository productTypeRepository;
    private Validator validator;

    @Override
    public ProductType save(ProductTypeDTO productTypeDTO) {

        ProductType productType = productTypeMapper.mapProductType(productTypeDTO);

        Set<ConstraintViolation<Object>> violations = validator.validate(productType);

        if(!violations.isEmpty()){
            throw new InvalidDataFieldException("Some of the fields have invalid have invalid data or no data at all", violations);
        }

        ProductType savedProductType;

        try {
            savedProductType = productTypeRepository.save(productType);
        } catch (Exception ex){
            ex.printStackTrace();
            throw new SaveException("Failed to save following product type with name: "+ productType.getName());
        }

        return savedProductType;
    }
}
