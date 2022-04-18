package com.rappid.company.service;

import com.rappid.company.dto.CompanyCreateRequest;
import com.rappid.company.exception.InvalidDataFieldException;
import com.rappid.company.exception.SaveException;
import com.rappid.company.mapper.CompanyMapper;
import com.rappid.company.model.Company;
import com.rappid.company.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final Validator validator;

    public void save(CompanyCreateRequest companySaveRequest){
          Company company =  companyMapper.mapToCompany(companySaveRequest);

          Set<ConstraintViolation<Object>> violations = validator.validate(company);

          if(!violations.isEmpty()) {
             throw new InvalidDataFieldException("Some of the fields have invalid have invalid data or no data at all", violations);
          }

          try{
              companyRepository.insert(company);
          }catch (Exception ex){
              ex.printStackTrace();
              throw new SaveException("Failed to save following company object: "+company.toString());
          }
    }
}
