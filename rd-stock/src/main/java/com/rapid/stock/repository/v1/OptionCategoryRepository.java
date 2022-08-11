package com.rapid.stock.repository.v1;

import com.rapid.stock.model.v1.OptionCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OptionCategoryRepository extends MongoRepository<OptionCategory, String> {
}
