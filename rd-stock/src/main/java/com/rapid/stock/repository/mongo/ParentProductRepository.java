package com.rapid.stock.repository.mongo;

import com.rapid.stock.model.mongo.ParentProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParentProductRepository extends MongoRepository<ParentProduct,String> {
}
