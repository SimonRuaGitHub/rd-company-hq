package com.rapid.stock.mapper.v1;

import com.rapid.stock.model.v1.Rack;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Profile("decrapted")
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
