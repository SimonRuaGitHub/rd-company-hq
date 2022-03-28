package com.rapid.stock.web.controller;

import com.rapid.stock.dto.OptionCategorySaveRequest;
import com.rapid.stock.service.OptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/option")
@AllArgsConstructor
public class OptionController {

    private final OptionService optionService;

    @PostMapping
    public ResponseEntity saveOptions(@RequestBody OptionCategorySaveRequest optionCategorySaveRequest){
        optionService.save(optionCategorySaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
