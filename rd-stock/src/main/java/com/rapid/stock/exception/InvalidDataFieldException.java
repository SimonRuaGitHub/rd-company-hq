package com.rapid.stock.exception;

import com.rapid.stock.model.ParentProduct;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class InvalidDataFieldException extends RuntimeException{

    private Set<ConstraintViolation<Object>> violations;

    public InvalidDataFieldException(String message, Set<ConstraintViolation<Object>> violations){
           super(message);
           this.violations = violations;
    }

    public Set<ConstraintViolation<Object>> getViolations() {
        return violations;
    }
}
