package com.rapid.stock.web.filter;

import com.rapid.stock.dto.RestExceptionResult;
import com.rapid.stock.dto.RestFieldErrors;
import com.rapid.stock.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionMapping {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestExceptionResult> handleEntityNotFound(NotFoundException ex){
           ex.printStackTrace();
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestExceptionResult(ex.getMessage()));
    }

    @ExceptionHandler(SaveException.class)
    public ResponseEntity<RestExceptionResult> handleUnableToSaveEntity(SaveException ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestExceptionResult(ex.getMessage()));
    }

    @ExceptionHandler(InvalidDataFieldException.class)
    public ResponseEntity<RestFieldErrors> handleInvalidDataFieldException(InvalidDataFieldException ex){

        ex.printStackTrace();
        List<String> errorsMsgsField = ex.getViolations().stream().map(v -> v.getMessage()).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RestFieldErrors.builder().errorMessages(errorsMsgsField).build());
    }

    @ExceptionHandler(DuplicatedReferenceException.class)
    public ResponseEntity<RestExceptionResult> handleDuplicatedReferenceException(DuplicatedReferenceException ex){

        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestExceptionResult(ex.getMessage()));
    }

    @ExceptionHandler(NotValidRackException.class)
    public ResponseEntity<RestExceptionResult> handleNotValidRackException(NotValidRackException ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestExceptionResult(ex.getMessage()));
    }

    @ExceptionHandler(NotValidOptionCategoryException.class)
    public ResponseEntity<RestExceptionResult> handleNotValidOptionCategoryException(NotValidOptionCategoryException ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestExceptionResult(ex.getMessage()));
    }
}
