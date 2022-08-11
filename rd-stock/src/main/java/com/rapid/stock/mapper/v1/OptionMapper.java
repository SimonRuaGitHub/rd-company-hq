package com.rapid.stock.mapper.v1;

import com.rapid.stock.dto.AvailabilityDTO;
import com.rapid.stock.dto.OptionCategorySaveRequest;
import com.rapid.stock.dto.OptionSaveRequest;
import com.rapid.stock.model.v1.Availability;
import com.rapid.stock.model.v1.Option;
import com.rapid.stock.model.v1.OptionCategory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("decrapted")
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

            if(optionsAvailabilityListDto == null || optionsAvailabilityListDto.isEmpty())
                return null;
           else
                return optionsAvailabilityListDto.stream()
                                                 .map(optionAvailabilityDto -> Availability.builder()
                                                                                .companySiteID(optionAvailabilityDto.getCompanySiteID())
                                                                                .quantityAvailable(optionAvailabilityDto.getQuantityAvailable())
                                                                                .createdAt(LocalDateTime.now())
                                                                                .build())
                                                 .collect(Collectors.toList());
    }
}
