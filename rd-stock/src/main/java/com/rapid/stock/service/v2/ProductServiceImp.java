package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.v2.ParentProductMapper;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.repository.v2.ParentProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
@AllArgsConstructor
@Profile("rational-db")
public class ProductServiceImp implements ProductService {

    private final ParentProductRepository productRepository;
    private final ParentProductMapper parentProductMapper;
    private final Validator validator;

    @Override
    public ParentProduct save(ParentProductSaveRequest parentProductDto) {
        ParentProduct parentProduct = parentProductMapper.mapSaveRequest(parentProductDto);

        Set<ConstraintViolation<Object>> violations = validator.validate(parentProduct);

        if(!violations.isEmpty()) {
            throw new InvalidDataFieldException("Some of the fields have invalid data or no data at all", violations);
        }

        ParentProduct savedProduct = null;

        try{
            savedProduct = productRepository.save(parentProduct);
        }catch(Exception ex){
            ex.printStackTrace();
            throw new SaveException("Failed to create following product with id: "+parentProduct.getId());
        }

        return savedProduct;
    }
}
