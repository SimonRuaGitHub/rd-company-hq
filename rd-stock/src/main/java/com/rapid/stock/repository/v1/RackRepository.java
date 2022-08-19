package com.rapid.stock.repository.v1;

import com.rapid.stock.model.v1.Rack;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

@Profile("decrapted")
public interface RackRepository extends MongoRepository<Rack,String> {
}
