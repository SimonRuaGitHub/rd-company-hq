package com.rapid.stock.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.dto.v2.ProductVersionSaveResponse;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.service.v2.ProductVersionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

@RestController
@RequestMapping("api/products/versions")
@RequiredArgsConstructor
@Slf4j
public class ProductVersionController {

    private final ProductVersionService productVersionService;
    private final ObjectMapper mapper;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ProductVersionSaveResponse> saveProductVersion(
            @RequestParam(value = "product-version") String jsonProductVersion,
            @RequestParam(value = "product-image") MultipartFile image
    ) {
        try {
            ProductVersionSaveRequest productVersionSaveRequest = mapper.readValue(
                            jsonProductVersion,
                            ProductVersionSaveRequest.class
                    );
            ProductVersionSaveResponse pvSaveResponse = productVersionService.save(productVersionSaveRequest, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(pvSaveResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<ProductVersion>> getAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Page<ProductVersion> pageProductVersions = productVersionService.getAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(pageProductVersions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productVersionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
