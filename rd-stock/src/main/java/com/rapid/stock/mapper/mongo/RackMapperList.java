package com.rapid.stock.mapper.mongo;

import com.rapid.stock.model.mongo.Rack;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RackMapperList {

    private final MongoTemplate mongoTemplate;

    public List<Rack> mapToRackEntities(List<String> rackIds) {
        if (rackIds != null && !rackIds.isEmpty()){
            return rackIds.stream()
                           .map(id -> mongoTemplate.findById(id, Rack.class))
                           .collect(Collectors.toList());

        } else {
            return null;
        }
    }
}
