package com.rapid.stock.model.rules;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class RacksSchemaRules {
    public boolean noParentRacksWithProducts(List<String> racksIds, List<String> productIds) {
              return  !(racksIds == null || racksIds.isEmpty()) &&
                      !(productIds == null || productIds.isEmpty());
    }
}
