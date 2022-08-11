package com.rapid.stock.repository.mongo;

import com.rapid.stock.model.mongo.Rack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RackRepository extends MongoRepository<Rack,String> {
}
