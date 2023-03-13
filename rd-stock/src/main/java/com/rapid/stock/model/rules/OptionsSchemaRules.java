package com.rapid.stock.model.rules;

import com.rapid.stock.exception.NotValidOptionCategoryException;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.repository.v2.OptionCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OptionsSchemaRules {

       private OptionCategoryRepository optionCategoryRepository;

       public String uniqueName(String name, String companyId) {

              if( optionCategoryRepository.existsByNameAndCompanyId(name, companyId) ){
                  throw new NotValidOptionCategoryException("Option category already existis with this name");
              }

              return name;
       }

        public String uniqueLabel(String label, String companyId) {

            if( optionCategoryRepository.existsByLabelAndCompanyId(label, companyId) ){
                throw new NotValidOptionCategoryException("Option category already existis with this label");
            }

            return label;
        }

        public List<ParentProduct> productsOfSameCompany(List<ParentProduct> products, String companyId) {
               if( products != null && !products.isEmpty() &&
                   products.stream().anyMatch( product -> !product.getCompanyId().equals(companyId)) )
                   throw new NotValidOptionCategoryException("Option category contains at least one product which doesn't belong to the same company");
               else
                   return products;
        }
}
