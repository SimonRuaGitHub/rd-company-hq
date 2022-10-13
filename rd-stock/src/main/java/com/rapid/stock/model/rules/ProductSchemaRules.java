package com.rapid.stock.model.rules;

import com.rapid.stock.exception.NotValidRackException;
import com.rapid.stock.model.v2.Rack;
import com.rapid.stock.repository.v2.ParentProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSchemaRules {

    private final ParentProductRepository productRepository;

    public List<Rack> childRacksOfSameCompany(List<Rack> childRacks, String companyId){
        if(!childRacks.stream().allMatch(childRack ->  childRack.getCompanyId().equals(companyId)))
            throw new NotValidRackException("Some child rack doesn't contain the corresponding companyId");
        else
            return childRacks;
    }
}
