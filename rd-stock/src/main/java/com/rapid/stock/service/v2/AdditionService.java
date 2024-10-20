package com.rapid.stock.service.v2;

import com.rapid.stock.dto.AdditionSaveRequest;
import com.rapid.stock.dto.AdditionSaveResponse;

public interface AdditionService {
     AdditionSaveResponse save(AdditionSaveRequest additionRequestSave);
}
