package com.rapid.stock.service.v2;

import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.v2.Mapper;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

public class GeneralSaveOperationService {

    private final Validator validator;
    private final Mapper mapper;
    private final JpaRepository repository;

    public <T,G> T save(G dto) {
        final T entity = (T) mapper.mapToEntity(dto);
        final Set<ConstraintViolation<Object>> violations = validator.validate(entity);

        if(!violations.isEmpty()) {
            throw new InvalidDataFieldException("Some of the fields have invalid data or no data at all", violations);
        }

        T savedEntity = null;

        try{
            savedEntity = (T) repository.save(entity);
        }catch(Exception ex){
            ex.printStackTrace();
            throw new SaveException("Failed to save information in database: "+ex.getMessage());
        }

        return savedEntity;
    }

    @Builder
    public GeneralSaveOperationService(Validator validator, Mapper mapper, JpaRepository repository) {
        this.validator = validator;
        this.mapper = mapper;
        this.repository = repository;
    }
}
