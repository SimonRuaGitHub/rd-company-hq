package com.rapid.stock.web.controller;

import com.rapid.stock.dto.ProductTypeDTO;
import com.rapid.stock.service.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product/type")
@AllArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @PostMapping
    public ResponseEntity saveType(@RequestBody ProductTypeDTO productTypeDTO){
           productTypeService.save(productTypeDTO);
           return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
