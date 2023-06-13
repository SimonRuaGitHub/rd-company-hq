package com.rapid.stock.web.controller;

import com.rapid.stock.dto.AvailabilityDTO;
import com.rapid.stock.service.v2.ProductAvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product/availability")
@AllArgsConstructor
public class ProductAvailabilityController {

    private final ProductAvailabilityService productAvailabilityService;

    @PostMapping
    public ResponseEntity saveProductAvailability(@RequestBody AvailabilityDTO availabilityDTO) {
        productAvailabilityService.save(availabilityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
