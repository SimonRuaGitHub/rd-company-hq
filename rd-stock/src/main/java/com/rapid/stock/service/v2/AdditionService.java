package com.rapid.stock.service.v2;

import com.rapid.stock.dto.AdditionSaveRequest;
import com.rapid.stock.dto.AdditionSaveResponse;
import com.rapid.stock.model.v2.Addition;
import com.rapid.stock.model.v2.ProductVersion;
import org.springframework.data.domain.Page;

public interface AdditionService {

     AdditionSaveResponse save(AdditionSaveRequest additionRequestSave);

     Page<Addition> getAll(int page, int size);

     void delete(Long additionId);

}
