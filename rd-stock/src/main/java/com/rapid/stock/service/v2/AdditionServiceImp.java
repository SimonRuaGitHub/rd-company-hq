package com.rapid.stock.service.v2;

import com.rapid.stock.dto.AdditionSaveRequest;
import com.rapid.stock.dto.AdditionSaveResponse;
import com.rapid.stock.mapper.v2.request.AdditionMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.AdditionMapperSaveResponse;
import com.rapid.stock.model.operations.GeneralSaveOperation;
import com.rapid.stock.model.v2.Addition;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.repository.v2.AdditionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
@AllArgsConstructor
public class AdditionServiceImp implements AdditionService {

    private final AdditionRepository additionRepository;
    private final AdditionMapperSaveRequest additionMapperSaveRequest;
    private final Validator validator;
    private final AdditionMapperSaveResponse additionMapperSaveResponse;

    @Override
    public AdditionSaveResponse save(AdditionSaveRequest additionRequestSave) {
        Addition addition = GeneralSaveOperation
                .builder()
                .mapper(additionMapperSaveRequest)
                .repository(additionRepository)
                .validator(validator)
                .build()
                .save(additionRequestSave);

        return additionMapperSaveResponse.map(addition);
    }
}
