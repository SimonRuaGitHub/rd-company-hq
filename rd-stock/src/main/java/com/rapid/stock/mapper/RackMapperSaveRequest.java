package com.rapid.stock.mapper;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.exception.ExistingProductException;
import com.rapid.stock.model.Rack;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RackMapperSaveRequest {

    private final ParentProductMapper parentProductMapper;
    private final RackMapperList rackMapperList;

    public Rack mapRackSaveRequest(RackDto rackDto){

        if (rackDto.getProductIds().stream().distinct().anyMatch(idToFind -> rackDto.getProductIds().stream().filter(id -> idToFind.equals(id)).count() > 1))
            throw new ExistingProductException("Product ids can't be repeated");

           return Rack.builderWithSubRacks()
                      .name(rackDto.getName())
                      .description(rackDto.getDescription())
                      .products(parentProductMapper.mapToParentProductForRacks(rackDto.getProductIds()))
                      .rackList(rackMapperList.mapToRackEntities(rackDto.getRacksIds()))
                      .companyId(rackDto.getCompanyId())
                      .build();
    }
}
