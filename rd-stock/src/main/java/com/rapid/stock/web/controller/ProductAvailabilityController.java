package com.rapid.stock.web.controller;

import com.rapid.stock.dto.AvailabilitySaveRequest;
import com.rapid.stock.model.v2.Availability;
import com.rapid.stock.service.v2.ProductAvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/availabilities")
@AllArgsConstructor
public class ProductAvailabilityController {

    private final ProductAvailabilityService productAvailabilityService;

    @PostMapping
    public ResponseEntity<Void> saveProductAvailability(@RequestBody AvailabilitySaveRequest availabilitySaveRequest) {
        productAvailabilityService.save(availabilitySaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Availability>> getAllProductAvailabilities(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Page<Availability> availabilities = productAvailabilityService.getAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(availabilities);
    }
}
