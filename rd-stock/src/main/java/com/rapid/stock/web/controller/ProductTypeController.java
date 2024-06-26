package com.rapid.stock.web.controller;

import com.rapid.stock.dto.ProductTypeSaveRequest;
import com.rapid.stock.dto.ProductTypeSaveResponse;
import com.rapid.stock.model.v2.ProductType;
import com.rapid.stock.service.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/type")
@AllArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @PostMapping
    public ResponseEntity<ProductTypeSaveResponse> saveType(@RequestBody ProductTypeSaveRequest productTypeSaveRequest){
           ProductTypeSaveResponse productTypeSaveResponse = productTypeService.save(productTypeSaveRequest);
           return ResponseEntity.status(HttpStatus.CREATED).body(productTypeSaveResponse);
    }

    @GetMapping(path = "/name")
    public ResponseEntity<List<String>> getAllTypeNames(){
        List<String> productTypeNames = productTypeService.getAllProductTypeNames();
        return ResponseEntity.status(HttpStatus.OK).body(productTypeNames);
    }

    @GetMapping
    public ResponseEntity<List<ProductType>> getAllTypes(){
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        return ResponseEntity.status(HttpStatus.OK).body(productTypes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable("id") Long typeId) throws Throwable {
        productTypeService.delete(typeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
