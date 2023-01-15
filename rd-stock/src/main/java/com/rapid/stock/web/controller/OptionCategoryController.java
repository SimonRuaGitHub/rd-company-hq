package com.rapid.stock.web.controller;

import com.rapid.stock.dto.OptionCategoryDTO;
import com.rapid.stock.service.v2.OptionCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/option/category")
@AllArgsConstructor
public class OptionCategoryController {

    private final OptionCategoryService optionCategoryService;

    @PostMapping
    public ResponseEntity saveOptionCategory(OptionCategoryDTO optionCategoryDTO){
           optionCategoryService.save(optionCategoryDTO);
           return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
