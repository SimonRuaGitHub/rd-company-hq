package com.rapid.stock.repository.v1;

import com.rapid.stock.model.v1.OptionCategory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

@Profile("decrapted")
public interface OptionCategoryRepository extends MongoRepository<OptionCategory, String> {
}
