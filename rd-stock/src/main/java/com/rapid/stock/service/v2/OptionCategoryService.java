package com.rapid.stock.service.v2;

import com.rapid.stock.dto.OptionCategoryDTO;
import com.rapid.stock.dto.OptionCategorySaveResponse;
import com.rapid.stock.model.v2.OptionCategory;
import org.springframework.data.domain.Page;

public interface OptionCategoryService {
    public OptionCategorySaveResponse save(OptionCategoryDTO optionCategoryDTO);

    public Page<OptionCategory> getAll(int page, int size);
}
