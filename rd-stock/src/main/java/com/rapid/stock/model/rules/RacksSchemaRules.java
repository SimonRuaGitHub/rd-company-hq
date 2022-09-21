package com.rapid.stock.model.rules;

import com.rapid.stock.exception.NotFoundException;
import com.rapid.stock.exception.NotValidRackException;
import com.rapid.stock.model.v2.Rack;
import com.rapid.stock.repository.v2.RackRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RacksSchemaRules {

    private final RackRepository rackRepository;

    public boolean noParentRacksWithProducts(List<String> racksIds, List<String> productIds) {
              return  !(racksIds == null || racksIds.isEmpty()) &&
                      !(productIds == null || productIds.isEmpty());
    }

    public String noRepeatedRackNameForCompany(String name, String companyId){
            if(rackRepository.existsByNameAndCompanyId(name, companyId))
                throw new NotValidRackException("There is already a rack name of: "+name+" created for company id: "+companyId);
            else
                return name;
    }

    public List<Rack> childRacksOfSameCompany(List<Rack> childRacks, String companyId){
           if(!childRacks.stream().allMatch(childRack ->  childRack.getCompanyId().equals(companyId)))
               throw new NotValidRackException("Some child rack doesn't contain the corresponding companyId");
           else
               return childRacks;
    }

    public Rack getExistingParentRack(Long id){
        if(id != null && id > 0)
            return rackRepository.findById(id)
                                 .orElseThrow(() -> new NotFoundException("Parent rack with id: "+id+" was not found"));
        else
            return null;
    }
}
