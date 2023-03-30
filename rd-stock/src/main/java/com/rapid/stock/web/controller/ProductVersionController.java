package com.rapid.stock.web.controller;

import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.service.v2.ProductVersionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/product/version")
@AllArgsConstructor
public class ProductVersionController {

    private final ProductVersionService productVersionService;

    @PostMapping
    public ResponseEntity<ProductVersion> saveProductVersion(@RequestBody ProductVersionSaveRequest productVersionSaveRequest){
           ProductVersion productVersion = productVersionService.save(productVersionSaveRequest);
           return ResponseEntity
                   .status(HttpStatus.CREATED).body(productVersion);
    }
}
