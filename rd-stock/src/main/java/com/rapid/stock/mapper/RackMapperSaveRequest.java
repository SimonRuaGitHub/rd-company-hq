package com.rapid.stock.mapper;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.exception.ExistingProductException;
import com.rapid.stock.exception.NotValidParentRack;
import com.rapid.stock.model.Rack;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RackMapperSaveRequest {

    private final ParentProductMapper parentProductMapper;
    private final RackMapperList rackMapperList;

    public Rack mapRackSaveRequest(RackDto rackDto){

        if (rackDto.getProductIds() != null && rackDto.getProductIds()
                                                      .stream()
                                                      .distinct()
                                                      .anyMatch(idToFind -> rackDto.getProductIds().stream().filter(id -> idToFind.equals(id)).count() > 1))
            throw new ExistingProductException("Product ids can't be repeated");

        if ( !(rackDto.getRacksIds() == null || rackDto.getRacksIds().isEmpty()) &&
             !(rackDto.getProductIds() == null || rackDto.getProductIds().isEmpty()) )
             throw new NotValidParentRack("Parent rack can't contain products and racks at the same time");

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
