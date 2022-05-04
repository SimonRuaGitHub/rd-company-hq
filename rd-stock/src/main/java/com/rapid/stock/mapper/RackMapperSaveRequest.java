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
public class RackMapperSaveRequest {

    private final ParentProductMapper parentProductMapper;
    private final RackMapperList rackMapperList;

    public Rack mapRackSaveRequest(RackDto rackDto){
           return Rack.builderWithSubRacks()
                      .name(rackDto.getName())
                      .description(rackDto.getDescription())
                      .products(parentProductMapper.mapToParentProductEntities(rackDto.getProductIds()))
                      .rackList(rackMapperList.mapToRackEntities(rackDto.getRacksIds()))
                      .companyId(rackDto.getCompanyId())
                      .build();
    }


}
