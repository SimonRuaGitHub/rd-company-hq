package com.rapid.stock.model.rules;

import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.Rack;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Component
public class GeneralSchemaRules {
    public boolean repeatedIDsInsideCollection(List<String> ids){
           return ids.stream()
                     .distinct()
                     .anyMatch(idToFind -> ids.stream().filter(id -> idToFind.equals(id)).count() > 1);
    }

    public <T> void validateBusinessObjectsOfSameCompany(List<T> listBussinessObjects, String companyId, Supplier<RuntimeException> exceptionSupplier) {
        if( listBussinessObjects != null && !listBussinessObjects.isEmpty() &&
            listBussinessObjects.stream().anyMatch( bussinessObject -> !getCompanyIdByObjectType(bussinessObject).orElse("").equals(companyId)) )
            throw exceptionSupplier.get();
    }

    private <T> Optional<String> getCompanyIdByObjectType(T businessObject) {

           if(businessObject instanceof OptionCategory)
              return Optional.of(((OptionCategory) businessObject).getCompanyId());
           else if(businessObject instanceof Rack)
              return Optional.of(((Rack) businessObject).getCompanyId());
           else if(businessObject instanceof ParentProduct)
              return Optional.of(((ParentProduct) businessObject).getCompanyId());
           else return Optional.empty();
    }
}
