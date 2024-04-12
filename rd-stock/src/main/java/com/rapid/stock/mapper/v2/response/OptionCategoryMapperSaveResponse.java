package com.rapid.stock.mapper.v2.response;

import com.rapid.stock.dto.OptionCategorySaveResponse;
import com.rapid.stock.model.v2.OptionCategory;
import org.springframework.stereotype.Component;

@Component
public class OptionCategoryMapperSaveResponse {

   public OptionCategorySaveResponse map(OptionCategory optionCategory) {
       return OptionCategorySaveResponse
               .builder()
               .id(optionCategory.getId())
               .name(optionCategory.getName())
               .company(optionCategory.getCompanyId())
               .build();
   }
}
