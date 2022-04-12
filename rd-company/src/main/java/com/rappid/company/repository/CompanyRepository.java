package com.rappid.company.repository;

import com.rappid.company.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company,String> {
}
