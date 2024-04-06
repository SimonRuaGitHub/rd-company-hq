package com.rapid.stock.web.controller;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.model.v2.Rack;
import com.rapid.stock.service.RackService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/racks")
@AllArgsConstructor
public class RackController {

    private final RackService rackService;

    @PostMapping
    public ResponseEntity<Void> saveRack(@RequestBody RackDto rackDto){
           rackService.save(rackDto);
           return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Rack>> getAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Page<Rack> racks = rackService.getAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(racks);
    }
}
