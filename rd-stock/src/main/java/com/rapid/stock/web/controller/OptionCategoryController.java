package com.rapid.stock.web.controller;

import com.rapid.stock.dto.OptionCategoryDTO;
import com.rapid.stock.dto.OptionCategorySaveResponse;
import com.rapid.stock.mapper.v2.response.OptionCategoryMapperSaveResponse;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.service.v2.OptionCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/option/categories")
@AllArgsConstructor
public class OptionCategoryController {

    private final OptionCategoryService optionCategoryService;

    @PostMapping
    public ResponseEntity<OptionCategorySaveResponse> saveOptionCategory(@RequestBody OptionCategoryDTO optionCategoryDTO) {
           OptionCategorySaveResponse optionCategorySaveResponse = optionCategoryService.save(optionCategoryDTO);
           return ResponseEntity.status(HttpStatus.CREATED).body(optionCategorySaveResponse);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<OptionCategory>> getAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
          Page<OptionCategory> optionCategories = optionCategoryService.getAll(page, size);
          return ResponseEntity.status(HttpStatus.OK).body(optionCategories);
    }
}
