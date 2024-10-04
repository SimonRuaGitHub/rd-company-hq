package com.rapid.stock.web.filter;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.rapid.stock.dto.RestExceptionResult;
import com.rapid.stock.dto.RestFieldErrors;
import com.rapid.stock.dto.S3ErrorOperation;
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

    @ExceptionHandler(NotValidProductException.class)
    public ResponseEntity<RestExceptionResult> handleNotValidProductException(NotValidProductException ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestExceptionResult(ex.getMessage()));
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<RestExceptionResult> handleNumberFormatException(NumberFormatException ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestExceptionResult(ex.getMessage()));
    }

    @ExceptionHandler(NotValidProductVersionException.class)
    public ResponseEntity<RestExceptionResult> handleNumberFormatException(NotValidProductVersionException ex){
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RestExceptionResult(ex.getMessage()));
    }

    @ExceptionHandler(S3Exception.class)
    public ResponseEntity<S3ErrorOperation> handleS3Exception(S3Exception ex) {
        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new S3ErrorOperation(
                        ex.getStatusCode(),
                        ex.getMessage(),
                        ex.getBucket(),
                        ex.getKey(),
                        ex.getOperationType().toString()
                )
        );
    }

    @ExceptionHandler(SdkClientS3Exception.class)
    public ResponseEntity<RestExceptionResult> handleS3Exception(SdkClientS3Exception ex) {
        SdkClientException exception = ex;
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RestExceptionResult(ex.getMessage()));
    }
}