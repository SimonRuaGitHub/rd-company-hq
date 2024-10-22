package com.rapid.stock.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rapid.stock.dto.AdditionMedataSaveRequest;
import com.rapid.stock.dto.AdditionSaveRequest;
import com.rapid.stock.dto.AdditionSaveResponse;
import com.rapid.stock.service.v2.AdditionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/option/addition")
@AllArgsConstructor
public class AdditionController {

    private final AdditionService additionService;
    private final ObjectMapper mapper;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<AdditionSaveResponse> saveAddition(
                   @RequestParam(value = "addition-info") String jsonAddition,
                   @RequestParam(value = "addition-image") MultipartFile image
    ) {
        try {
           AdditionMedataSaveRequest additionMedataSaveRequest = mapper.readValue(
                   jsonAddition,
                   AdditionMedataSaveRequest.class
           );

           AdditionSaveRequest additionSaveRequest = AdditionSaveRequest.builder()
                   .additionMedataSaveRequest(additionMedataSaveRequest)
                   .image(image)
                   .build();

           AdditionSaveResponse additionSaveResponse = additionService.save(additionSaveRequest);

           return ResponseEntity.status(HttpStatus.CREATED).body(additionSaveResponse);
           
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}