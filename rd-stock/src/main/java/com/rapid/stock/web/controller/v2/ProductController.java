package com.rapid.stock.web.controller.v2;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.service.v2.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/product")
@AllArgsConstructor
@Profile("rational-db")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody ParentProductSaveRequest ppSaveRequest){
           productService.save(ppSaveRequest);
           return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
