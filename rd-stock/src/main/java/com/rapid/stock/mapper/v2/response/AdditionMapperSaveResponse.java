package com.rapid.stock.mapper.v2.response;

import com.rapid.stock.dto.AdditionSaveResponse;
import com.rapid.stock.dto.OptionCategorySaveResponse;
import com.rapid.stock.model.v2.Addition;
import com.rapid.stock.model.v2.OptionCategory;

public class AdditionMapperSaveResponse {

    public AdditionSaveResponse map(Addition addition) {
        return new AdditionSaveResponse(addition.getId());
    }
}
