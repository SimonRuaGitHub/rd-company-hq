package com.rapid.stock.web.controller;

import com.rapid.stock.dto.AvailabilitySaveRequest;
import com.rapid.stock.dto.AvailabilitySaveResponse;
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
    public ResponseEntity<AvailabilitySaveResponse> saveProductAvailability(@RequestBody AvailabilitySaveRequest availabilitySaveRequest) {
        AvailabilitySaveResponse availabilitySaveResponse = productAvailabilityService.save(availabilitySaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(availabilitySaveResponse);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Availability>> getAllProductAvailabilities(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Page<Availability> availabilities = productAvailabilityService.getAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(availabilities);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productAvailabilityService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
