package com.rapid.stock.mapper;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.model.Rack;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RackMapper {

    private final MongoTemplate mongoTemplate;
    private final ParentProductMapper parentProductMapper;

    public Rack mapRackSaveRequest(RackDto rackDto){
           return Rack.builder()
                      .name(rackDto.getName())
                      .description(rackDto.getDescription())
                      .products(parentProductMapper.mapToParentProductEntities(rackDto.getProductIds()))
                      .build();
    }

    public List<Rack> mapToRackEntities(List<String> rackIds) {

        if (rackIds != null || rackIds.isEmpty()){
            return rackIds.stream()
                    .map(id -> mongoTemplate.findById(id, Rack.class))
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
