package com.rapid.stock.service.v2;

import com.rapid.stock.dto.OptionCategorySaveResponse;
import com.rapid.stock.mapper.v2.request.OptionCategoryMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.OptionCategoryMapperSaveResponse;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.repository.v2.OptionCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
@AllArgsConstructor
@Profile("rational-db")
public class OptionCategoryServiceImp implements OptionCategoryService {

    private final OptionCategoryMapperSaveRequest optionCategoryMapperSaveRequest;
    private final OptionCategoryMapperSaveResponse optionCategoryMapperSaveResponse;
    private final Validator validator;
    private final OptionCategoryRepository optionCategoryRepository;

    @Override
    public OptionCategorySaveResponse save(OptionCategoryDTO optionCategoryDTO) {
        OptionCategory optionCategory = GeneralSaveOperationService
                .builder()
                .mapper(optionCategoryMapperSaveRequest)
                .repository(optionCategoryRepository)
                .validator(validator)
                .build()
                .save(optionCategoryDTO);

        return optionCategoryMapperSaveResponse.map(optionCategory);
    }

    @Override
    public Page<OptionCategory> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return optionCategoryRepository.findAll(pageRequest);
    }
}
