package com.rapid.stock.repository.v1;

import com.rapid.stock.model.v1.ParentProduct;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Profile("decrapted")
@Component("ProductRepositoryMongo")
public interface ParentProductRepository extends MongoRepository<ParentProduct,String> {
}
