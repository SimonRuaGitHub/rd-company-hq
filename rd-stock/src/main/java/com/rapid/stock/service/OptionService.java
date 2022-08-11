package com.rapid.stock.service;

import com.rapid.stock.dto.OptionCategorySaveRequest;
import com.rapid.stock.model.mongo.OptionCategory;

public interface OptionService {
    public OptionCategory save(OptionCategorySaveRequest optionCategorySaveRequest);
}
