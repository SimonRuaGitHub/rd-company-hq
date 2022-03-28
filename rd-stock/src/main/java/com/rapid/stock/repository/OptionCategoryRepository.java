package com.rapid.stock.repository;

import com.rapid.stock.model.OptionCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OptionCategoryRepository extends MongoRepository<OptionCategory, String> {
}
