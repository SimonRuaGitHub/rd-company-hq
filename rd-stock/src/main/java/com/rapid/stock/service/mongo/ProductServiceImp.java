package com.rapid.stock.service.mongo;

import com.rapid.stock.dto.ParentProductSaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.mongo.ParentProductMapper;
import com.rapid.stock.model.mongo.ParentProduct;
import com.rapid.stock.repository.mongo.ParentProductRepository;
import com.rapid.stock.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ParentProductRepository productRepository;
    private final ParentProductMapper parentProductMapper;
    private final Validator validator;
    private final MongoTemplate mongoTemplate;

    @Override
    public ParentProduct save(ParentProductSaveRequest parentProductDto) {

         ParentProduct parentProduct = parentProductMapper.mapSaveRequest(parentProductDto);

         Set<ConstraintViolation<Object>> violations = validator.validate(parentProduct);

        if(!violations.isEmpty()) {
            throw new InvalidDataFieldException("Some of the fields have invalid have invalid data or no data at all", violations);
        }

        ParentProduct product = null;

         try{
               product = productRepository.insert(parentProduct);
         }catch(Exception ex){
               ex.printStackTrace();
               throw new SaveException("Failed to create following product with id: "+parentProduct.getProductId());
         }

         return product;
    }
}
