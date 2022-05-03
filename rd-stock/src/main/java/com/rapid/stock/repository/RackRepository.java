package com.rapid.stock.repository;

import com.rapid.stock.model.Rack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RackRepository extends MongoRepository<Rack,String> {
}
