package com.rappid.company.web.filter;

import com.rappid.company.dto.RestExceptionResult;
import com.rappid.company.dto.RestFieldErrors;
import com.rappid.company.exception.InvalidDataFieldException;
import com.rappid.company.exception.NotFoundException;
import com.rappid.company.exception.SaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionMapping {

    @ExceptionHandler(SaveException.class)
    public ResponseEntity<RestExceptionResult> handleUnableToSaveEntity(SaveException ex){
           ex.printStackTrace();
           return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestExceptionResult> handleEntityNotFound(NotFoundException ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestExceptionResult(ex.getMessage()));
    }

    @ExceptionHandler(InvalidDataFieldException.class)
    public ResponseEntity<RestFieldErrors> handleUnableToSaveEntity(InvalidDataFieldException ex){

        ex.printStackTrace();
        List<String> errorsMsgsField = ex.getViolations().stream().map(v -> v.getMessage()).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RestFieldErrors.builder().errorMessages(errorsMsgsField).build());
    }
}
