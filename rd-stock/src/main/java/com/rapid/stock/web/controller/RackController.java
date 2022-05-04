package com.rapid.stock.web.controller;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.service.RackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rack")
@AllArgsConstructor
public class RackController {

    private final RackService rackService;

    @PostMapping
    public ResponseEntity saveRack(@RequestBody RackDto rackDto){
           rackService.save(rackDto);
           return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
