package com.rapid.stock.repository.v1;

import com.rapid.stock.model.v1.ParentProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParentProductRepository extends MongoRepository<ParentProduct,String> {
}
