package com.rapid.stock.repository;

import com.rapid.stock.model.ParentProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParentProductRepository extends MongoRepository<ParentProduct,String> {
}
