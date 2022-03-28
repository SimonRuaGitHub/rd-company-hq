package com.rapid.stock.mapper;

import com.rapid.stock.dto.OptionCategorySaveRequest;
import com.rapid.stock.dto.OptionSaveRequest;
import com.rapid.stock.model.Option;
import com.rapid.stock.model.OptionCategory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OptionMapper {
    public OptionCategory mapCategorySaveRequest(OptionCategorySaveRequest optionCategorySaveRequest){
           return OptionCategory.builder()
                                .name(optionCategorySaveRequest.getName())
                                .descrip(optionCategorySaveRequest.getDescription())
                                .label(optionCategorySaveRequest.getLabel())
                                .options(mapOptionsSaveRequest(optionCategorySaveRequest.getOptions()))
                                .build();
    }

    public List<Option> mapOptionsSaveRequest(List<OptionSaveRequest> optionsSaveRequestList){
           return optionsSaveRequestList.stream()
                                        .map(optionSaveRequest -> Option.builder()
                                                                        .id(optionSaveRequest.getId())
                                                                        .name(optionSaveRequest.getName())
                                                                        .price(optionSaveRequest.getPrice())
                                                                        .build())
                                        .collect(Collectors.toList());
    }
}
