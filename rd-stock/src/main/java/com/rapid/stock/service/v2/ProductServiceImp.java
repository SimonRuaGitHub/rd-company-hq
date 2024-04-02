package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.v2.ParentProductMapper;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.repository.v2.ParentProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
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
        return GeneralSaveOperationService
                .builder()
                .mapper(parentProductMapper)
                .repository(productRepository)
                .validator(validator)
                .build()
                .save(parentProductDto);
    }

    @Override
    public Page<ParentProduct> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return productRepository.findAll(pageRequest);
    }
}
