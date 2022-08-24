package com.rapid.stock.service;

import com.rapid.stock.dto.OptionCategorySaveRequest;

public interface OptionService<T> {
    public T save(OptionCategorySaveRequest optionCategorySaveRequest);
}
