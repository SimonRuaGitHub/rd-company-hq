package com.rapid.stock.mapper.v1;

import com.rapid.stock.dto.RackSaveRequest;
import com.rapid.stock.exception.DuplicatedReferenceException;
import com.rapid.stock.exception.NotValidRackException;
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

    public Rack mapRackSaveRequest(RackSaveRequest rackSaveRequest){

        if (rackSaveRequest.getProductIds() != null && generalSchemaRules.repeatedIDsInsideCollection(rackSaveRequest.getProductIds()))
            throw new DuplicatedReferenceException("Product ids can't be repeated");

        if ( rackSchemaRules.noParentRacksWithProducts(rackSaveRequest.getRacksIds(), rackSaveRequest.getProductIds()) )
             throw new NotValidRackException("Parent rack can't contain products and racks at the same time");

           Rack rack = Rack.builder()
                           .name(rackSaveRequest.getName())
                           .description(rackSaveRequest.getDescription())
                           .products(parentProductMapper.mapToParentProductForRacks(rackSaveRequest.getProductIds()))
                           .racks(rackMapperList.mapToRackEntities(rackSaveRequest.getRacksIds()))
                           .companyId(rackSaveRequest.getCompanyId())
                           .build();
           return rack;
    }
}
