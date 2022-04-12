package com.rappid.company.repository;

import com.rappid.company.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category,String> {
}
