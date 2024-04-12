package com.rapid.stock.mapper.v2.request;

import com.rapid.stock.dto.RackSaveRequest;
import com.rapid.stock.exception.NotValidRackException;
import com.rapid.stock.mapper.v2.CommonMapper;
import com.rapid.stock.model.rules.GeneralSchemaRules;
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
public class RackMapperSaveRequest implements MapperRequest<Rack, RackSaveRequest> {

    private final CommonMapper commonMapper;
    private final ParentProductRepository productRepository;
    private final RackRepository rackRepository;
    private final Util util;
    private final RacksSchemaRules racksSchemaRules;
    private final GeneralSchemaRules generalSchemaRules;

    public Rack mapToEntity(RackSaveRequest rackSaveRequest){

        if ( racksSchemaRules.noParentRacksWithProducts(rackSaveRequest.getRacksIds(), rackSaveRequest.getProductIds()) )
            throw new NotValidRackException("Parent rack can't contain products and racks at the same time");

           return Rack.builder()
                      .name(getRackNameValidated(rackSaveRequest.getName(), rackSaveRequest.getCompanyId()))
                      .description(rackSaveRequest.getDescription())
                      .childRacks(getChildRacks(rackSaveRequest.getRacksIds(), rackSaveRequest.getCompanyId()))
                      .companyId(rackSaveRequest.getCompanyId())
                      .parentRack(getParentRack(rackSaveRequest.getParentRackId()))
                      .products(getProducts(rackSaveRequest.getProductIds(), rackSaveRequest.getCompanyId()))
                      .build();
    }

    private List<ParentProduct> getProducts(List<String> productIds, String companyId) {
            List<ParentProduct> mappedProducts = commonMapper.mapToEntitiesByIds(util.parseStringListToLong(productIds), productRepository);
            generalSchemaRules.validateBusinessObjectsOfSameCompany(
                    mappedProducts,
                    companyId,
                    () -> new NotValidRackException("Rack contains at least one product which doesn't belong to the same company"));
            return mappedProducts;
    }

    private List<Rack> getChildRacks(List<String> rackIds, String companyId){
            List<Rack> childRacks = commonMapper.mapToEntitiesByIds(util.parseStringListToLong(rackIds), rackRepository);

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
