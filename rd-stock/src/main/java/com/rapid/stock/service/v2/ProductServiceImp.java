package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.dto.v2.ParentProductSaveResponse;
import com.rapid.stock.exception.NotFoundException;
import com.rapid.stock.mapper.v2.request.ParentProductMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.ParentProductMapperSaveResponse;
import com.rapid.stock.model.operations.GeneralDeleteOperation;
import com.rapid.stock.model.operations.GeneralSaveOperation;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.repository.v2.ParentProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ParentProductRepository productRepository;
    private final ParentProductMapperSaveRequest parentProductMapperSaveRequestMapper;
    private final ParentProductMapperSaveResponse parentProductMapperSaveResponse;
    private final Validator validator;

    @Override
    public ParentProductSaveResponse save(ParentProductSaveRequest parentProductDto) {
         ParentProduct parentProduct = GeneralSaveOperation
                                            .builder()
                                            .mapper(parentProductMapperSaveRequestMapper)
                                            .repository(productRepository)
                                            .validator(validator)
                                            .build()
                                            .save(parentProductDto);

         return parentProductMapperSaveResponse.map(parentProduct);
    }

    @Override
    public Page<ParentProduct> getAll(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        return productRepository.findAll(pageRequest);
    }

    @Override
    public void delete(Long productId) {
        GeneralDeleteOperation
                .builder()
                .repository(productRepository)
                .exceptionSupplier(() -> new NotFoundException("Parent product not found with ID: " + productId))
                .build()
                .delete(productId);
    }
}
