package com.rappid.company.service;

import com.rappid.company.dto.CompanyCreateRequest;
import com.rappid.company.mapper.CompanyMapper;
import com.rappid.company.model.Company;
import com.rappid.company.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public void save(CompanyCreateRequest companySaveRequest){
          Company company =  companyMapper.mapToCompany(companySaveRequest);
          companyRepository.insert(company);
    }
}
