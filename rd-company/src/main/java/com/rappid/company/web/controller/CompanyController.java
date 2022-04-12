package com.rappid.company.web.controller;

import com.rappid.company.dto.CompanyCreateRequest;
import com.rappid.company.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity createCompany(@RequestBody CompanyCreateRequest companySaveRequest){
           companyService.save(companySaveRequest);
           return  ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
