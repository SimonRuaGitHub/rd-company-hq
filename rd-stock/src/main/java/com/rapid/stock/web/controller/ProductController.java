package com.rapid.stock.web.controller;

import com.rapid.stock.dto.ParentProductSaveRequest;
import com.rapid.stock.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody ParentProductSaveRequest ppSaveRequest){
           productService.save(ppSaveRequest);
           return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
