package com.rapid.stock.service.v2;

import com.rapid.stock.dto.OptionCategorySaveRequest;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.service.OptionService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Profile("rational-db")
public class OptionServiceImp implements OptionService {

    @Override
    public OptionCategory save(OptionCategorySaveRequest optionCategorySaveRequest) {
           return OptionCategory.builder().build();
    }
}
