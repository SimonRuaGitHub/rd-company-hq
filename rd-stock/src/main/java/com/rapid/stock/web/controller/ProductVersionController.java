package com.rapid.stock.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.service.v2.ProductVersionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

@RestController
@RequestMapping("api/product/version")
@RequiredArgsConstructor
@Slf4j
public class ProductVersionController {

    private final ProductVersionService productVersionService;
    private final ObjectMapper mapper;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ProductVersion> saveProductVersion(
            @RequestParam(value = "product-version") String jsonProductVersion,
            @RequestParam(value = "product-image") MultipartFile image
    ) {
        try {
            ProductVersionSaveRequest productVersionSaveRequest = mapper.readValue(
                            jsonProductVersion,
                            ProductVersionSaveRequest.class
                    );
            ProductVersion productVersion = productVersionService.save(productVersionSaveRequest, image);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
