package com.rapid.stock.mapper.v2;

import com.rapid.stock.dto.RackDto;
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

    public Rack mapRackSaveRequest(RackDto rackDto){

           return Rack.builder()
                      .name(rackDto.getName())
                      .description(rackDto.getDescription())
                      .childRacks(getRacks(rackDto.getRacksIds()))
                      .companyId(rackDto.getCompanyId())
                      .build();
    }

    private List<ParentProduct> getProducts(List<String> productIds){
            return mapperList.mapToEntitiesByIds(util.parseStringListToLong(productIds), productRepository);
    }

    private List<Rack> getRacks(List<String> rackIds){
            return mapperList.mapToEntitiesByIds(util.parseStringListToLong(rackIds), rackRepository);
    }
}
