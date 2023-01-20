package com.rapid.stock.model.rules;

import com.rapid.stock.exception.NotValidOptionCategoryException;
import com.rapid.stock.repository.v2.OptionCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
}
