package com.rapid.stock.repository.mongo;

import com.rapid.stock.model.mongo.OptionCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OptionCategoryRepository extends MongoRepository<OptionCategory, String> {
}
