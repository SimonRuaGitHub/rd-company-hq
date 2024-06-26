package com.rapid.stock.service.v2;

import com.rapid.stock.exception.NotFoundException;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.repository.v2.ParentProductRepository;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Supplier;

public class GeneralDeleteOperationService {
    private final JpaRepository repository;
    private final Supplier<RuntimeException> exceptionSupplier;

    @Builder
    public GeneralDeleteOperationService(
            JpaRepository repository,
            Supplier<RuntimeException> exceptionSupplier
    ) {
        this.repository = repository;
        this.exceptionSupplier = exceptionSupplier;
    }

    public <T, ID> void delete(ID identifier) {
        final T entity;
        try {
            entity = (T) repository.findById(identifier).orElseThrow(exceptionSupplier);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        repository.delete(entity);
    }
}
