package com.rapid.stock.web.controller;

import com.rapid.stock.dto.ProductTypeDTO;
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
    public ResponseEntity<Void> saveType(@RequestBody ProductTypeDTO productTypeDTO){
           productTypeService.save(productTypeDTO);
           return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllTypeNames(){
        List<String> productTypeNames = productTypeService.getAllProductTypeNames();
        return ResponseEntity.status(HttpStatus.OK).body(productTypeNames);
    }
}
