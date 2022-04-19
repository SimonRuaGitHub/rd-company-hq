package com.rapid.stock.mapper;

import com.rapid.stock.dto.AvailabilityDTO;
import com.rapid.stock.dto.OptionCategorySaveRequest;
import com.rapid.stock.dto.OptionSaveRequest;
import com.rapid.stock.model.Availability;
import com.rapid.stock.model.Option;
import com.rapid.stock.model.OptionCategory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    private List<Option> mapOptionsSaveRequest(List<OptionSaveRequest> optionsSaveRequestList){
           return optionsSaveRequestList.stream()
                                        .map(optionSaveRequest -> Option.builder()
                                                                        .id(optionSaveRequest.getId())
                                                                        .name(optionSaveRequest.getName())
                                                                        .price(optionSaveRequest.getPrice())
                                                                        .optionAvalabilities( mapToOptionAvailibilityList(optionSaveRequest.getOptionAvailabilities()) )
                                                                        .build())
                                        .collect(Collectors.toList());
    }

    private List<Availability> mapToOptionAvailibilityList(List<AvailabilityDTO> optionsAvailabilityListDto){
            return optionsAvailabilityListDto.stream()
                                             .map(optionAvailabilityDto -> Availability.builder()
                                                                                       .companySiteID(optionAvailabilityDto.getCompanySiteID())
                                                                                       .quantityAvailable(optionAvailabilityDto.getQuantityAvailable())
                                                                                       .createdAt(LocalDateTime.now())
                                                                                       .build())
                                             .collect(Collectors.toList());
    }
}
