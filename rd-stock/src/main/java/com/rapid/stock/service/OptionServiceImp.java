package com.rapid.stock.service;

import com.rapid.stock.dto.OptionCategorySaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.OptionMapper;
import com.rapid.stock.model.OptionCategory;
import com.rapid.stock.repository.OptionCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.security.InvalidAlgorithmParameterException;
import java.util.Set;

@Service
@AllArgsConstructor
public class OptionServiceImp implements OptionService{

    private final OptionCategoryRepository optionCategoryRepository;
    private final OptionMapper optionMapper;
    private final Validator validator;

    @Override
    public OptionCategory save(OptionCategorySaveRequest optionCategorySaveRequest) {

          @Valid OptionCategory optionCategory = optionMapper.mapCategorySaveRequest(optionCategorySaveRequest);

           Set<ConstraintViolation<Object>> violations = validator.validate(optionCategory);

           if(!violations.isEmpty())
              throw new InvalidDataFieldException("Some of the fields have invalid have invalid data or no data at all", violations);

           try{
              return optionCategoryRepository.insert(optionCategory);
           }catch (Exception ex){
               ex.printStackTrace();
               throw new SaveException("Failed to save following option name: "+optionCategory.getName());
           }
    }
}
