package com.rapid.stock.web.controller;

import com.rapid.stock.dto.RackSaveRequest;
import com.rapid.stock.dto.RackSaveResponse;
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
    public ResponseEntity<RackSaveResponse> saveRack(@RequestBody RackSaveRequest rackSaveRequest){
           RackSaveResponse rackSaveResponse = rackService.save(rackSaveRequest);
           return ResponseEntity.status(HttpStatus.CREATED).body(rackSaveResponse);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Rack>> getAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        Page<Rack> racks = rackService.getAll(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(racks);
    }

    @DeleteMapping("/{rackId}")
    public ResponseEntity<Void> delete(@PathVariable Long rackId) {
        rackService.delete(rackId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
