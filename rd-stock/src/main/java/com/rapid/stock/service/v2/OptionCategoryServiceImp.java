package com.rapid.stock.service.v2;

import com.rapid.stock.dto.OptionCategoryDTO;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.v2.OptionCategoryMapper;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.repository.v2.OptionCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
@AllArgsConstructor
@Profile("rational-db")
public class OptionCategoryServiceImp implements OptionCategoryService {

    private final OptionCategoryMapper optionCategoryMapper;
    private final Validator validator;
    private final OptionCategoryRepository optionCategoryRepository;

    @Override
    public OptionCategory save(OptionCategoryDTO optionCategoryDTO) {
        return GeneralSaveOperationService
                .builder()
                .mapper(optionCategoryMapper)
                .repository(optionCategoryRepository)
                .validator(validator)
                .build()
                .save(optionCategoryDTO);
    }
}
