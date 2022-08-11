package com.rapid.stock.repository.v1;

import com.rapid.stock.model.v1.Rack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RackRepository extends MongoRepository<Rack,String> {
}
