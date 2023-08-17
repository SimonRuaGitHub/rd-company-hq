package com.rapid.stock.service.v2;

import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.v2.Mapper;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class GeneralSaveOperationService<T,Z> {

    private final Validator validator;
    private final Mapper mapper;
    private final JpaRepository<T, ?> repository;

    public <G> T save(G dto) {
        T entity = (T) mapper.mapToEntity(dto);
        Set<ConstraintViolation<Object>> violations = validator.validate(entity);

        if(!violations.isEmpty()) {
            throw new InvalidDataFieldException("Some of the fields have invalid data or no data at all", violations);
        }

        T savedEntity = null;

        try{
            savedEntity = repository.save(entity);
        }catch(Exception ex){
            ex.printStackTrace();
            throw new SaveException("Failed to create following entity: "+savedEntity);
        }

        return savedEntity;
    }

    @Builder
    public GeneralSaveOperationService(Validator validator, Mapper mapper, JpaRepository<T, Long> repository) {
        this.validator = validator;
        this.mapper = mapper;
        this.repository = repository;
    }
}
