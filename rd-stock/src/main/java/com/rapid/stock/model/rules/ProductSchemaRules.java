package com.rapid.stock.model.rules;

import com.rapid.stock.exception.NotValidProductException;
import com.rapid.stock.exception.NotValidRackException;
import com.rapid.stock.repository.v2.ParentProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSchemaRules {

    private final ParentProductRepository productRepository;

    public String noRepeatedProductNameForCompany(String name, String companyId){
        if(productRepository.existsByNameAndCompanyId(name, companyId))
            throw new NotValidProductException("There is already a product name of: "+name+" created for company id: "+companyId);
        else
            return name;
    }
}
