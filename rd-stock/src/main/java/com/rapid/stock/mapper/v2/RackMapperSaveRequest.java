package com.rapid.stock.mapper.v2;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.exception.NotFoundException;
import com.rapid.stock.exception.NotValidRackException;
import com.rapid.stock.model.rules.RacksSchemaRules;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.Rack;
import com.rapid.stock.repository.v2.ParentProductRepository;
import com.rapid.stock.repository.v2.RackRepository;
import com.rapid.stock.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Profile("rational-db")
public class RackMapperSaveRequest {

    private final MapperList mapperList;
    private final ParentProductRepository productRepository;
    private final RackRepository rackRepository;
    private final Util util;
    private final RacksSchemaRules racksSchemaRules;

    public Rack mapRackSaveRequest(RackDto rackDto){

        if ( racksSchemaRules.noParentRacksWithProducts(rackDto.getRacksIds(), rackDto.getProductIds()) )
            throw new NotValidRackException("Parent rack can't contain products and racks at the same time");

           return Rack.builder()
                      .name(getRackNameValidated(rackDto.getName(), rackDto.getCompanyId()))
                      .description(rackDto.getDescription())
                      .childRacks(getChildRacks(rackDto.getRacksIds(), rackDto.getCompanyId()))
                      .companyId(rackDto.getCompanyId())
                      .parentRack(getParentRack(rackDto.getParentRackId()))
                      .build();
    }

    private List<ParentProduct> getProducts(List<String> productIds) {
            return mapperList.mapToEntitiesByIds(util.parseStringListToLong(productIds), productRepository);
    }

    private List<Rack> getChildRacks(List<String> rackIds, String companyId){
            List<Rack> childRacks = mapperList.mapToEntitiesByIds(util.parseStringListToLong(rackIds), rackRepository);

            if(childRacks != null && !childRacks.isEmpty())
               return racksSchemaRules.childRacksOfSameCompany(childRacks, companyId);

            return childRacks;
    }

    private Rack getParentRack(Long id){
            return racksSchemaRules.getExistingParentRack(id);
    }

    private String getRackNameValidated(String name, String companyId){
            return racksSchemaRules.noRepeatedRackNameForCompany(name, companyId);
    }
}
