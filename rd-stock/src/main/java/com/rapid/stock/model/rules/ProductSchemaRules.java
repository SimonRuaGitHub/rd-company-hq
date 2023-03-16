package com.rapid.stock.model.rules;

import com.rapid.stock.exception.NotValidProductException;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.repository.v2.ParentProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSchemaRules {

    private final ParentProductRepository productRepository;

    public List<OptionCategory> optionCategoriesOfSameCompany(List<OptionCategory> optionCategories, String companyId) {
        if( optionCategories != null && !optionCategories.isEmpty() &&
            optionCategories.stream().anyMatch( optionCategory -> !optionCategory.getCompanyId().equals(companyId)) )
            throw new NotValidProductException("Product contains at least one option which doesn't belong to the same company");
        else
            return optionCategories;
    }

    public void validateNoRepeatedProductNameForCompany(String name, String companyId){
        if(productRepository.existsByNameAndCompanyId(name, companyId))
           throw new NotValidProductException("There is already a product with the name of "+name+" inside the company");
    }
}
