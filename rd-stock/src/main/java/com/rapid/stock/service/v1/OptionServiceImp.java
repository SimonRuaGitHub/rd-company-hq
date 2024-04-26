package com.rapid.stock.service.v1;

import com.rapid.stock.dto.OptionCategorySaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.model.v1.OptionCategory;
import com.rapid.stock.repository.v1.OptionCategoryRepository;
import com.rapid.stock.service.OptionService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

@Service
@AllArgsConstructor
@Profile("decrapted")
public class OptionServiceImp implements OptionService {

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
