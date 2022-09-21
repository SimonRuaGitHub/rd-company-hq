package com.rapid.stock.mapper.v1;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.exception.DuplicatedReferenceException;
import com.rapid.stock.exception.NotValidParentRackException;
import com.rapid.stock.model.v1.Rack;
import com.rapid.stock.model.rules.GeneralSchemaRules;
import com.rapid.stock.model.rules.RacksSchemaRules;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Profile("decrapted")
public class RackMapperSaveRequest {

    private final ParentProductMapper parentProductMapper;
    private final RackMapperList rackMapperList;
    private final RacksSchemaRules rackSchemaRules;
    private final GeneralSchemaRules generalSchemaRules;

    public Rack mapRackSaveRequest(RackDto rackDto){

        if (rackDto.getProductIds() != null && generalSchemaRules.repeatedIDsInsideCollection(rackDto.getProductIds()))
            throw new DuplicatedReferenceException("Product ids can't be repeated");

        if ( rackSchemaRules.noParentRacksWithProducts(rackDto.getRacksIds(), rackDto.getProductIds()) )
             throw new NotValidParentRackException("Parent rack can't contain products and racks at the same time");

           Rack rack = Rack.builder()
                           .name(rackDto.getName())
                           .description(rackDto.getDescription())
                           .products(parentProductMapper.mapToParentProductForRacks(rackDto.getProductIds()))
                           .racks(rackMapperList.mapToRackEntities(rackDto.getRacksIds()))
                           .companyId(rackDto.getCompanyId())
                           .build();
           return rack;
    }
}
