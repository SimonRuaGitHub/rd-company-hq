package com.rapid.stock.service.v2;

import com.rapid.stock.dto.OptionCategoryDTO;
import com.rapid.stock.model.v2.OptionCategory;

public interface OptionCategoryService {
    public OptionCategory save(OptionCategoryDTO optionCategoryDTO);
}
