package com.rapid.stock.model.operations;

import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Supplier;

public class GeneralDeleteOperation {
    private final JpaRepository repository;
    private final Supplier<RuntimeException> exceptionSupplier;

    @Builder
    public GeneralDeleteOperation(
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
